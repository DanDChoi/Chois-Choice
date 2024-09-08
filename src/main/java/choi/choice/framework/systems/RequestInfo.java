package choi.choice.framework.systems;

import choi.choice.framework.utils.RemoteAddrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@NoArgsConstructor
public class RequestInfo {
	String[] APPS = {"IOS_APP", "AOS_APP"};
   	public static final String USER_TRACKING_ID_COOKIE_KEY = "UTID";
   	public static final String WISELOG_TRACKING_ID_COOKIE_KEY = "PCID";
   	static final int TRACKING_ID_EXPIRE_SECONDS = Integer.MAX_VALUE;

   	HttpServletRequest request;
   	HttpServletResponse response;
   	SystemContext systemContext;
   	UserContextInjector userContextInjector;

   	String remoteAddress;
   	String requestURI;
   	String queryString;
   	String referer;
   	String actionId;
   	String requestId;
   	String threadId;
   	String userAgent;
   	String clientApp;
   	String clientAppVersion;
   	String memerNo;
   	String memberTypeCode;
   	String userTrackingId;
   	String trafficTracking;
   	String sessionId;
   	private Language language;

   	public RequestInfo(HttpServletRequest request, HttpServletResponse response, SystemContext systemContext,
   			UserContextInjector userContextInjector) {
   		super();
   		this.request = request;
   		this.response = response;
   		this.systemContext = systemContext;
   		this.userContextInjector = userContextInjector;
   		decide();
   	}

   	public void decide() {
   		decideRemoteAddress();
   		decideUrl();
   		decideReferer();
   		decideRequestId();
   		decideActionId();
   		decideThreadId();
   		decideMemberNo();
   		decideMemberType();
   //		decideUserTrackingId();
   		decideTrafficTracking();

   		decideLanguage();

   		decideUserAgent();

   		if (checkIsApp()) {
   			decideAppInfo();
   		}
   		decideSessionId();
   	}

   	private void decideSessionId() {
   		HttpSession session = request.getSession(false);
   		if (session == null) {
   			this.sessionId = "";
   		} else {
   			this.sessionId = session.getId();
   		}

   	}

   	private void decideActionId() {
   		actionId = request.getHeader("ACTION_ID");
   	}

   	private void decideTrafficTracking() {
   		trafficTracking = request.getHeader("X-AWS-Traffic");
   	}

   	void decideRequestId() {
   		this.requestId = systemContext.nextRequestId();
   	}

   	void decideRemoteAddress() {
   		this.remoteAddress = RemoteAddrUtil.getRemoteAddr(request);
   	}

   	void decideUrl() {
   		this.requestURI = request.getRequestURI();
   		this.queryString = request.getQueryString();
   	}

   	void decideReferer() {
   		String referer = request.getHeader("Referer");
   		if (StringUtils.isBlank(referer)) {
   			return;
   		}
   		this.referer = referer;
   	}

   	void decideThreadId() {
   		this.threadId = String.valueOf(Thread.currentThread().getId());
   	}

   	void decideMemberNo() {
   		Object principal = getCurrentUserPricipal();
   		if (principal == null) {
   			return;
   		}
   		String mbrNo = userContextInjector.getCurrentUserId(request, principal);
   		if (StringUtils.isNotEmpty(mbrNo)) {
   			decideMemberNo(mbrNo);
   		}
   	}

   	void decideMemberType() {
   		Object principal = getCurrentUserPricipal();
   		if (principal == null) {
   			return;
   		}
   		String mbrTpCd = userContextInjector.getCurrentUserType(request, principal);
   		if (StringUtils.isNotEmpty(mbrTpCd)) {
   			decideMemberTypeCode(mbrTpCd);
   		}
   	}

   //	/**
   //	 * 기존의 SID가 의미 해석에 혼돈을 주기 때문에 제거하고 UTID(User tracking ID)로 변경 또한 생성 알고리즘을
   //	 * Wiselog와 일치시키고 Wiselog용 PCID 쿠키도 동시에 생성하게 변경
   //	 */
   //	void decideUserTrackingId() {
   //
   //		String pcid = CookieUtil.getCookieValue(request, WISELOG_TRACKING_ID_COOKIE_KEY);
   //		if (StringUtils.isEmpty(pcid)) {
   //			pcid = systemContext.nextUid();
   //			CookieUtil.addCookie(response, WISELOG_TRACKING_ID_COOKIE_KEY, pcid, "ssfshop.com",
   //					TRACKING_ID_EXPIRE_SECONDS);
   //		}
   //
   //		String utid = CookieUtil.getCookieValue(request, USER_TRACKING_ID_COOKIE_KEY);
   //		if (StringUtils.isEmpty(utid)) {
   //			utid = pcid; // PCID의 우선순위가 높다
   //			CookieUtil.addCookie(response, USER_TRACKING_ID_COOKIE_KEY, utid, "ssfshop.com",
   //					TRACKING_ID_EXPIRE_SECONDS);
   //		}
   //
   //		injectUserTrackingId(utid);
   //	}

   	void decideLanguage() {
   		this.language = Language.KOREAN;
   		// setup default language
   		systemContext.setCurrentRequestLanguage(Language.KOREAN);

   		switch (systemContext.getAppType()) {
   		default:
   			break;
   		}
   	}

   	void decideUserAgent() {
   		if (StringUtils.isNotEmpty(request.getHeader("User-Agent"))) {
   			this.userAgent = request.getHeader("User-Agent");
   		}
   	}

   	boolean checkIsApp() {
   		return StringUtils.isNotEmpty(request.getHeader("User-Agent"))
   				&& request.getHeader("User-Agent").contains("APPVER:");
   	}

   	void decideAppInfo() {
   		String userAgent = request.getHeader("User-Agent");

   		for (String app : APPS) {
   			if (userAgent.contains(app)) {
   				this.clientApp = app;
   				int appVersionIndex = userAgent.indexOf("APPVER:");
   				if (appVersionIndex > -1) {
   					int sidx = appVersionIndex+"APPVER:".length();
   					int eidx = userAgent.indexOf(',', sidx);
   					if (eidx > 0) {
   						this.clientAppVersion = userAgent.substring(sidx, eidx);
   					} else {
   						this.clientAppVersion = "";
   					}
   				}

   				break;
   			}
   		}
   	}

   	Object getCurrentUserPricipal() {
   		HttpSession session = request.getSession(false);
   		if (session == null) {
   			return null;
   		}
   		SecurityContext sc = (SecurityContext) session
   				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
   		if (sc == null) {
   			return null;
   		}
   		Authentication auth = sc.getAuthentication();
   		if (auth == null || ((auth instanceof UsernamePasswordAuthenticationToken) == false
   				&& (auth instanceof RememberMeAuthenticationToken) == false)) {
   			return null;
   		}
   		Object principal = auth.getPrincipal();
   		return principal;
   	}

   	void decideMemberNo(String memberNo) {
   		this.memerNo = memberNo;
   	}

   	void decideMemberTypeCode(String mbrTpCd) {
   		this.memberTypeCode = mbrTpCd;
   	}

   	public void injectUserTrackingId(String userTrackingId) {
   		this.userTrackingId = userTrackingId;
   	}
}
