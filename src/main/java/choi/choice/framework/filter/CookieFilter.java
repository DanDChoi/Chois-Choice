package choi.choice.framework.filter;

import choi.choice.framework.commons.StringService;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * ios를 제외하고 쿠키에 https와 same site NONE을 강제하는 필터
 * 이러한 처리를 하지 않는 경우 크롬 브라우저의 강화된 보안정책에 따라 삼사쿠키가 제한되어 외부 결제 연동시 세션이 초기화되는 문제가 발생할 수 있다.
 */

@Slf4j
public class CookieFilter implements Filter {


    private final static String[] apps = {"iphone", "ipad", "ipod"};
    private final static String SESSION = "SESSION";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userAgent = httpServletRequest.getHeader("User-Agent");
        boolean isIos12 = false;
        if (StringService.isNotEmpty(userAgent)) {
            userAgent = userAgent.toLowerCase();
            for (String app : apps) {
                if (userAgent.contains(app)) {
                    if (userAgent.contains("os 12_")
                            || userAgent.contains("os 11_")
                            || userAgent.contains("os 10_")
                            || userAgent.contains("os 9_")
                            || userAgent.contains("os 8_")
                            || userAgent.contains("os 7_")
                            || userAgent.contains("os 6_")
                            || userAgent.contains("os 5_")
                            ) {
                        isIos12 = true;
                        break;
                    }
                }
            }
        }

        if (!isIos12) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            addSameSite(httpServletRequest, httpServletResponse, "None");
        }
        chain.doFilter(request, response);
    }

    private void addSameSite(HttpServletRequest httpServletRequest, HttpServletResponse response, String sameSite) {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) {
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; HttpOnly; SameSite=%s;", header, sameSite));
                firstHeader = false;
                continue;
            }

            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; HttpOnly; SameSite=%s;", header, sameSite));
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        if (!CollectionService.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                // SESSION COOKIE 만 SAMESITE NONE 처리
                if(SESSION.equals(cookie.getName())) {
                    CookieUtil.addCookie(response, cookie, sameSite);
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}
