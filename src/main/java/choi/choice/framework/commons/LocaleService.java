package choi.choice.framework.commons;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleService {
    /**
   	 * 시스템 정책에 따라서 현재 로케일 정보를 리턴 한다.
   	 *
   	 * <p/>
   	 *
   	 * getLocaleService().getCurrentLocale(request);
   	 *
   	 * @param request
   	 *            HttpServletRequest
   	 * @return Locale 시스템 정책에 맞는 로케일 오브젝트 리턴
   	 * @since 2015
   	 */
   	public static Locale getCurrentLocale(HttpServletRequest request) {
   		return RequestContextUtils.getLocale(request);
   	}

   	/* 쓰레드 로컬에 저장된 현재 HttpServletRequest 객체를 얻는다. */
   	public static Locale getCurrentLocale() {
   		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
   		return RequestContextUtils.getLocale(request);
   	}

   	/**
   	 * 시스템 정책에 따라서 현재 로케일 enum 정보를 리턴 한다.
   	 *
   	 * <p/>
   	 *
   	 * getLocaleService().getCurrentLang(request);
   	 *
   	 * @param request
   	 *            HttpServletRequest
   	 * @return Lang 시스템 정책에 맞는 enum 오브젝트 리턴
   	 * @since 2015
   	 */
   	public static String getCurrentLang(HttpServletRequest request) {

   		Locale locale = getCurrentLocale(request);
   		String localeLang = locale.getISO3Language().toUpperCase();
   		/*
   		final String chinaLang = "CHI";

   		// 중문일 경우만 변환 한다.
   		if (localeLang.equals(chinaLang) || localeLang.equals("ZHO")) {
   			localeLang = chinaLang;
   		}
   		*/

   		// 국문만 제공하므로 KOR 로 강제 변경 처리
   		localeLang = "KOR";

   		return localeLang;
   	}
}
