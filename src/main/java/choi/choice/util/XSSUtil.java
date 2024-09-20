package choi.choice.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * The Class XSSUtil.
 */
@Slf4j
public class XSSUtil {

    /**
     * <p>
     * description about class
     * </p>
     * Sanitize.
     *
     * @param string the string
     * @return the string
     */
    public static String sanitize(String string) {

        if (string == null) {
            return "";
        }
        String value = "";
        try{
            value = string.replaceAll("(?i)<script.*?>.*?</script.*?>", "")
                    .replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "")
                    .replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
            value = escapeHTML(value);
        }catch(Exception e){
            log.error("",e);
        }
        return StringUtils.trimToEmpty(string);
    }

    /**
     * <p>
     * description about class
     * </p>
     * Escape html.
     *
     * @param value the value
     * @return the string
     */
    public static String escapeHTML (String value) {
        if (value == null) {
            return null;
        }
        String str = value;
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("'", "&#39;");
        return str;
//        String str = value;
//        str = str.replaceAll("\\<", "&lt;");
//        str = str.replaceAll("\\>", "&gt;");
//        return str;
    }

    /**
     * <p>
     * description about class
     * </p>
     * Unescape html.
     *
     * @param value the value
     * @return the string
     */
    public static String unescapeHTML (String value) {
        String str = value;
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&gt;", ">" );
        return str;
    }

    /**
     * Builds the random id.
     *
     * @return the string
     */
    public static String buildRandomId () {

        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String stripXSS(String value , String... args ) {
        if (value != null) {
            try{
                value = value.replaceAll("\"", "");

                Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<marquee>(.*?)</marquee>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<a>(.*?)</a>", Pattern.CASE_INSENSITIVE);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");
                scriptPattern = Pattern.compile("onmouse(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                value = scriptPattern.matcher(value).replaceAll("");

                value = escapeHTML(value);
                if (args != null && args.length > 0) {
                    if (Arrays.asList(args).contains("br")) {
                        value = value.replaceAll("\n", " <br/> " );
                    }
                }
            }catch(Exception e){
                log.error("",e);
            }
        }
        return value;
    }

    /**
     * <p>
     * XSS 패턴이 있는지 확인하는 메서드
     * </p>
     *
     * @param value 검사할 문자열
     * @return XSS 패턴이 발견되면 true, 그렇지 않으면 false
     */
    public static boolean checkXSS(String value) {
        if (value != null) {
            try {
                // 모든 XSS 패턴을 컴파일
                Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("<marquee>(.*?)</marquee>", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("<a>(.*?)</a>", Pattern.CASE_INSENSITIVE);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("onclick(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

                scriptPattern = Pattern.compile("onmouse(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                if (scriptPattern.matcher(value).find()) return true;

            } catch (Exception e) {
                log.error("XSS 패턴을 검사하는 중 오류 발생", e);
            }
        }
        return false; // XSS 패턴이 발견되지 않음
    }

}

