package choi.choice.framework.systems;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
@Slf4j
public class ExecutionContext {
    @Autowired
    @Getter
    SystemContext systemContext;

    @Autowired(required = false)
    UserContextInjector userContextInjector;

    public static final String URL = "reqeustUri";
    public static final String QUERY_STRING = "requestQuery";
    public static final String REFERER = "referer";
    public static final String REMOTE_ADDR = "remoteAddr";
    public static final String REQUEST_ID = "requestId";
    public static final String ACTION_ID = "actionId";
    public static final String PROGRAM_ID = "programId";
    public static final String THREAD_ID = "threadId";
    public static final String MEMBER_NO = "mbrNo";
    public static final String LANGUAGE = "lang";
    public static final String MEMBER_TYPE_CODE = "mbrTpCd";
    public static final String USER_TRACKING_ID = "utid";
    public static final String USER_AGENT = "userAgent";
    public static final String ORDER_NUMBER = "orderNumber";

    public static final String CLIENT_APP = "clientApp";
    public static final String CLIENT_APP_VERSION = "clientAppVersion";
    public static final String TRAFFIC_TRACK = "trafficTrack";

    public static final String SESSION_ID = "sessionId";

    public static final String X_HEADER_MDC_CALLER_REQUEST_ID = "X-MDC-CALLER-REQUEST-ID";
    public static final String X_HEADER_MDC_CALLER_MEMBER_ID = "X-MDC-CALLER-MEMBER-ID";
    public static final String X_HEADER_MDC_CALLER_ORDER_NUMBER = "X-MDC-CALLER-ORDER-NUMBER";

    public static RequestInfo currentRequestInfo() {
   		return requestInfo.get();
   	}

   	public static void setCurrentRequestInfo(RequestInfo info) {
   		ExecutionContext.requestInfo.set(info);
   	}

   	public void inject(HttpServletRequest request, HttpServletResponse response) {
   		RequestInfo info = new RequestInfo(request, response, systemContext, userContextInjector);
   		requestInfo.set(info);

   		request.setAttribute("REQUEST_INFO", info);

   		inject(info);

   	}

   	public static void inject(RequestInfo info) {
   		MDC.put(USER_AGENT, info.getUserAgent());
   		MDC.put(REMOTE_ADDR, info.getRemoteAddress());

   		MDC.put(URL, info.getRequestURI());
   		MDC.put(QUERY_STRING, info.getQueryString());
   		MDC.put(REFERER, info.getReferer());
   		MDC.put(REQUEST_ID, info.getRequestId());
   		if (info.getActionId() != null) {
   			MDC.put(ACTION_ID, info.getActionId());
   		}

   		MDC.put(THREAD_ID, info.getThreadId());

   		MDC.put(MEMBER_NO, info.getMemerNo());
   		MDC.put(MEMBER_TYPE_CODE, info.getMemberTypeCode());
   		MDC.put(TRAFFIC_TRACK, info.getTrafficTracking());

   		MDC.put(USER_TRACKING_ID, info.getUserTrackingId());
   		MDC.put(LANGUAGE, (info.getLanguage() == null ? Language.KOREAN.name() : info.getLanguage().name()));
   //        systemContext.setCurrentRequestLanguage(info.getLanguage());

   		MDC.put(SESSION_ID, info.getSessionId());

   		if (info.checkIsApp()) {
   			MDC.put(CLIENT_APP, info.getClientApp());
   			MDC.put(CLIENT_APP_VERSION, info.getClientAppVersion());
   		}

           // set caller tracking information to MDC
           for (String headerName : getXHeaderNames()) {
               String headerValue = info.getRequest().getHeader(headerName);
               if (headerValue != null && headerValue.length() > 0) {
                   String mdcName = toCallerField(headerName);
                   MDC.put(mdcName, headerValue);
               }
           }
   	}

   	public void injectProgramId(HandlerMethod hm) {
   		MDC.put(PROGRAM_ID, ProgramId.from(hm));
   	}

   	@SuppressWarnings("rawtypes")
   	public void injectProgramId(Class clazz) {
   		MDC.put(PROGRAM_ID, ProgramId.from(clazz));
   	}

   	public void injectRequestId() {
   		MDC.put(REQUEST_ID, systemContext.nextRequestId());
   	}

   	public void cleanCurrentThread() {
   		requestInfo.remove();
   //		MDC.remove(URL);
   //		MDC.remove(QUERY_STRING);
   //		MDC.remove(REFERER);
   //		MDC.remove(REQUEST_ID);
   //		MDC.remove(ACTION_ID);
   //		MDC.remove(THREAD_ID);
   //		MDC.remove(REMOTE_ADDR);
   //
   //		// com.glyde.mall.framework.systems.ContextInfoInterceptor 에서 넣은 내용을
   //		// cleanup한다.
   //		MDC.remove(PROGRAM_ID);
   //		MDC.remove(MEMBER_NO);
   //		MDC.remove(MEMBER_TYPE_CODE);
   //		MDC.remove(USER_AGENT);
   //		MDC.remove(LANGUAGE);
   //		MDC.remove(CLIENT_APP);
   //		MDC.remove(CLIENT_APP_VERSION);
   //		MDC.remove(TRAFFIC_TRACK);
   //		MDC.remove(SESSION_ID);

   //		Map<String, String> map = MDC.getCopyOfContextMap();
   //		for(String key: map.keySet()) {
   //			MDC.remove(key);
   //		}
   		MDC.clear();

   		// reset to default language
   		systemContext.setCurrentRequestLanguage(Language.KOREAN);
   	}

       //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       // Helper method
       private static Collection<String> X_HEADER_NAMES = null;
       /**
        * get values that file name's prefix is X_HEADER_
        */
       public static Collection<String> getXHeaderNames() {
           if (X_HEADER_NAMES != null) {
               return X_HEADER_NAMES;
           }
           try {
               Collection<String> collection = new LinkedList<>();
               Field[] fields = ExecutionContext.class.getDeclaredFields();
               for (Field field : fields) {
                   if (field.getName().startsWith("X_HEADER_")) {
                       Object obj = field.get(null);
                       if (obj != null && obj.toString().length() > 0) {
                           collection.add(obj.toString());
                       }
                   }
               }
               X_HEADER_NAMES = collection;
           } catch (Exception e) {
               log.warn("ERROR occured during get field name of LoggerConstants:"+e.getMessage(), e);
           }
           return X_HEADER_NAMES;
       }

   //    /**
   //     * get XHeader Name and matched MDC value pairs
   //     */
   //    public static Map<String, String> getXHeaderNameAndValues() {
   //        Map<String, String> map = new HashMap<>();
   //
   //        for (String value : getXHeaderNames()) {
   //            if (!value.startsWith("X-CALLER-")) {
   //                if (log.isDebugEnabled()) {
   //                    log.debug("invalid value: {}", value);
   //                }
   //                continue;
   //            }
   //            String subValue = value.substring("X-CALLER-".length()).toLowerCase();
   //            String mdcValue = MDC.get(subValue) == null ? "" : MDC.get(subValue).toString();
   //            map.put(value, mdcValue);
   //        }
   //
   //        return map;
   //    }

       public static Map<String, String> getCallerHeaders() {
       	Map<String, String> map = new HashMap<>();

       	map.put(X_HEADER_MDC_CALLER_REQUEST_ID, MDC.get(REQUEST_ID));
       	map.put(X_HEADER_MDC_CALLER_MEMBER_ID, MDC.get(MEMBER_NO));
       	map.put(X_HEADER_MDC_CALLER_ORDER_NUMBER, MDC.get(ORDER_NUMBER));

       	return map;
       }

       public static String toCallerField(String xHeaderName) {
           if (xHeaderName == null) {
               return "";
           }
           if (xHeaderName.startsWith("X-MDC-")) {
           	String value = xHeaderName.substring("X-MDC-".length()).toLowerCase();
           	value = StringUtils.replaceChars(value, '-', '_');
               return value;
           }else {
               return "";
           }

       }
}
