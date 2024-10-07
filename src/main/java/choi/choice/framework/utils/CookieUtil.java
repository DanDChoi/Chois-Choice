package choi.choice.framework.utils;

import com.google.common.net.HttpHeaders;
import org.apache.commons.lang3.time.FastDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CookieUtil {
    public static final int SECONDS_OF_DAY = 60 * 60 * 24;
   	public static final int MAXAGE_SESSION = -1;
   	public static final int MAXAGE_TERMINATION = 0;

   	public static final String DEFAULT_PATH = "/root_hhe";
       private static final FastDateFormat expiresDateFormat= FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss zzz", TimeZone.getTimeZone("GMT"));

   	public static String getCookieValue(HttpServletRequest request, String name) {
   		Cookie cookies[] = request.getCookies();

   		if (cookies != null) {
   			for (Cookie cookie : cookies) {
   				if (cookie.getName().equals(name)) {
   					try {
   						String value = cookie.getValue();
   						return value;
   					} catch (Exception e) {
   						return "";
   					}
   				}
   			}
   		}
   		return null;
   	}

    /**
   	 * 쿠키에 새로운 정보를 기록한다. (만료일 = Browser Session 주기)
   	 *
   	 * @param HttpServletResponse
   	 * @param String
   	 *            name / Cookie name
   	 * @param String
   	 *            value / Cookie value
   	 */
   	public static void addCookie(HttpServletResponse response, String name, String value) {
   		addCookie(response, name, value, MAXAGE_SESSION);
   	}

   	/**
   	 * 쿠키 정보를 삭제한다.
   	 *
   	 * @param HttpServletResponse
   	 * @param String
   	 *            name / Cookie name
   	 * @param String
   	 *            value / Cookie value
   	 */
   	public static void deleteCookie(HttpServletResponse response, String name) {
   		addCookie(response, name, "", MAXAGE_TERMINATION);
   	}

   	/**
   	 * 쿠키에 새로운 정보를 기록한다. (만료일 = 입력한 날짜)
   	 *
   	 * @param HttpServletResponse
   	 * @param String
   	 *            name / Cookie name
   	 * @param String
   	 *            value / Cookie value
   	 * @param int
   	 *            expireDay / Cookie 만료일
   	 */
   	public static void addCookie(HttpServletResponse response, String name, String value, int expireSeconds) {
   		addCookie(response, name, value, null, expireSeconds);
   	}

   	public static void addCookie(HttpServletResponse response, String name, String value, String domain, int expireSeconds) {
   		addCookie(response, name, value, domain, DEFAULT_PATH, expireSeconds, true);
   	}

   	public static void addCookie(HttpServletResponse response, String name, String value, String domain, String path,
   			int seconds, boolean isSecure) {
   		/**
   		 * fortify - HTTP응답분할 조치
   		 */
   		name = SecureCode.httpSplit(name);
   		Cookie cookie = new Cookie(name, value);
   		cookie.setMaxAge(seconds < 0 ? MAXAGE_SESSION : seconds);

   		if (domain != null) {
   			cookie.setDomain(domain);
   		}

   		if(StringService.isEmpty(path)) {
   			path = "/";
   		}
   		cookie.setPath(path);

   		cookie.setSecure(isSecure);

   		response.addCookie(cookie);
   	}

   	/**
   	 * 쿠키 저장 시 SameSite 조정이 필요 할 시
   	 * @param response
   	 * @param cookie
   	 * @param sameSite
   	 */
    public static void addCookie(HttpServletResponse response, Cookie cookie, String sameSite) {
        StringBuilder c = new StringBuilder();
        c.append(cookie.getName());
        c.append('=');
        c.append(cookie.getValue());

        append2cookie(c, "domain", cookie.getDomain());
        String path = cookie.getPath();
        if(StringService.isEmpty(path)) {
            path = "/";
        }
        append2cookie(c, "path", path);

        if (cookie.getMaxAge() >= 0) {
           append2cookie(c, "Expires", getExpires(cookie.getMaxAge()));
        }

        append2cookie(c, "SameSite", sameSite);
        if ("None".equals(sameSite)) {
            c.append("; secure");
            c.append("; HttpOnly");
        } else {
            if (cookie.isHttpOnly()) {
                c.append("; HttpOnly");
            }

            if (cookie.getSecure()) {
                c.append("; secure");
            }
        }
        response.addHeader(HttpHeaders.SET_COOKIE, c.toString());
    }

    private static String getExpires(int maxAge) {
       if (maxAge < 0) {
           return "";
       }
       Calendar expireDate = Calendar.getInstance();
       expireDate.setTime(new Date());
       expireDate.add(Calendar.SECOND, maxAge);

       return expiresDateFormat.format(expireDate);
    }

    private static void append2cookie(StringBuilder cookie, String key, String value) {
       if (key == null ||
               value == null ||
               key.trim().equals("")
               || value.trim().equals("")) {
           return;
       }

       cookie.append("; ");
       cookie.append(key);
       cookie.append('=');
       cookie.append(value);
    }
}
