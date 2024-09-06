package choi.choice.framework.systems;

import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProgramId {
    static Map<HandlerMethod, String> idCache = new ConcurrentHashMap<>();

    public static String from(HandlerMethod hm) {
        synchronized (idCache) {
            if (idCache.containsKey(hm)) {
                return idCache.get(hm);
            }
            Method 메소드 = hm.getMethod();
            String id = from(메소드);
            idCache.put(hm, id);
            return id;
        }
    }

    public static String from(Class<?> clazz) {
        return from(clazz.getName());
    }

    public static String from(Method 메소드) {
        String 메소드명 = 메소드.getName();

        Class<?> 메소드선언한_클래스 = 메소드.getDeclaringClass();
        String 클래스명 = 메소드선언한_클래스.getName();

        return from(클래스명 + "." + 메소드명);
    }

    public static String from(String fullPath) { return shortenTokens(fullPath.split("\\."));}

    static String shorten(String token) {
        StringBuffer buf = new StringBuffer();
        shorten(token, buf);
        return buf.toString();
    }

    static void shorten(String token, StringBuffer buf) {
        buf.append(token.charAt(0));
        for (int i = 0; i < token.length(); i++) {
            char ch = token.charAt(i);
            if (Character.isUpperCase(ch)) {
                buf.append(ch);
            }
        }
    }

    static String shortenTokens(String[] full) {
        StringBuffer buf = new StringBuffer();
        for (String token : full) {
            shorten(token, buf);
        }
        return buf.toString();
    }
}
