package choi.choice.framework.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RemoteAddrUtil {
    static final String[] FORWARDED_HEADERS = {"X-Real-For", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    /**
     * 캡쳐된 첫번째 CLIENT IP 사용
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        for (String header : FORWARDED_HEADERS) {
            String ip = request.getHeader(header);
            if (StringUtils.isNotEmpty(ip)) {
                if (ip.indexOf(",") > -1) {
                    String[] split = ip.split(",");
                    ip = split[0];
                }
                return ip;
            }
        }

        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr.indexOf(",") > -1) {
            String[] split = remoteAddr.split(",");
            remoteAddr = split[0];
        }

        return remoteAddr;
    }
}
