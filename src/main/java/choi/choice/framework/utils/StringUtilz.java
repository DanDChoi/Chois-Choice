package choi.choice.framework.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

public class StringUtilz {

    public static String substr(String str, int start, int end) {
   		String result = "";
   		if (str.length() > 0 && start <= str.length() && end <= str.length()
   		        && start <= end)
   			result = str.substring(start, end);

   		return result;
   	}

   	public static String getDecimalFormat(double doubleNumber, int fraction) {
   		String result = "";

   		java.text.DecimalFormat numberFormat = new java.text.DecimalFormat();

   		if (fraction > 4)
   			fraction = 0;

   		switch (fraction) {
   		case 0:
   			numberFormat.applyPattern("#,##0");
   			break;
   		case 1:
   			numberFormat.applyPattern("#,##0.0");
   			break;
   		case 2:
   			numberFormat.applyPattern("#,##0.00");
   			break;
   		case 3:
   			numberFormat.applyPattern("#,##0.000");
   			break;
   		case 4:
   			numberFormat.applyPattern("#,##0.0000");
   			break;
   		case 5:
   			numberFormat.applyPattern("#0");
   			break;
   		default:
   			numberFormat.applyPattern("#,##0.00");
   			break;
   		}

   		try {
   			result = numberFormat.format(doubleNumber);
   		}
   		catch (IllegalArgumentException e) {
   			throw e;
   		}

   		return result;
   	}

   	public static String getDecimalFormat(String str, int fraction)
   	        throws Exception {
   		String stringNumber = str;
   		double doubleNumber = 0;

   		if (stringNumber.equals(""))
   			stringNumber = "0";

   		try {
   			doubleNumber = Double.parseDouble(stringNumber);
   		}
   		catch (NumberFormatException e) {
   			throw e;
   		}

   		return getDecimalFormat(doubleNumber, fraction);
   	}

   	public static String getDecimalFormat(Object obj, int fraction)
   	        throws Exception {
   		String stringNumber = obj.toString();
   		double doubleNumber = 0;

   		if (stringNumber.equals(""))
   			stringNumber = "0";

   		try {
   			doubleNumber = Double.parseDouble(stringNumber);
   		}
   		catch (NumberFormatException e) {
   			throw e;
   		}

   		return getDecimalFormat(doubleNumber, fraction);
   	}

   	public static String getDecimalFormat(long number, int fraction) {
   		Long longNumber = new Long(number);
   		double doubleNumber = longNumber.doubleValue();
   		return getDecimalFormat(doubleNumber, fraction);
   	}

   	public static boolean isInteger(String str) {
   		for (int i = 0; i < str.length(); i++) {
   			if ((str.charAt(i) < '0' || str.charAt(i) > '9')
   			        && str.charAt(i) == '-')
   				return false;
   		}
   		return true;
   	}

   	public static boolean isFloat(String str) {
   		for (int i = 0; i < str.length(); i++) {
   			if ((str.charAt(i) < '0' || str.charAt(i) > '9')
   			        && str.charAt(i) == '-' && str.charAt(i) == '.')
   				return false;
   		}
   		return true;
   	}

   	public static int strToInt(String num) {
   		int intNumber = 0;
   		if (num == null) {
   			num = "0";
   		}
   		try {
   			intNumber = Integer.parseInt(num);
   		}
   		catch (NumberFormatException e) {
   		}

   		return intNumber;
   	}

   	public static int strToInt(String num, int def) {

   		int intNumber = 0;

   		if (num == null) {
   			num = def + "";
   		}

   		try {
   			intNumber = Integer.parseInt(num);
   		}
   		catch (NumberFormatException e) {
   		}

   		return intNumber;
   	}

   	public static double strToDouble(String num) {
   		double doubleNumber = 0;

   		if (num == null || num.equals("0"))
   			num = "0.0";

   		try {
   			doubleNumber = Double.parseDouble(num);
   		}
   		catch (NumberFormatException e) {
   			throw e;
   		}

   		return doubleNumber;
   	}

   	public static float strToFloat(String num) {
   		float floatNumber = 0;

   		if (num == null || num.equals("0"))
   			num = "0.0";

   		try {
   			floatNumber = Float.parseFloat(num);
   		}
   		catch (NumberFormatException e) {
   			throw e;
   		}

   		return floatNumber;
   	}

   	public static String rplc(String str, String pattern, String replace) {
   		int s = 0;
   		int e = 0;
   		StringBuffer result = new StringBuffer();

   		while ((e = str.indexOf(pattern, s)) >= 0) {
   			result.append(str.substring(s, e));
   			result.append(replace);
   			s = e + pattern.length();
   		}

   		result.append(str.substring(s));

   		return result.toString();
   	}

   	public static String[] split(String strTarget,
   								   String strDelim,
   								   boolean bContainNull) {
   		int index = 0;
   		String[] resultStrArray = null;
   		resultStrArray = new String[search(strTarget, strDelim) + 1];
   		String strCheck = new String(strTarget);
   		while (strCheck.length() != 0) {
   			int begin = strCheck.indexOf(strDelim);
   			if (begin == -1) {
   				resultStrArray[index] = strCheck;
   				break;
   			} else {
   				int end = begin + strDelim.length();
   				if (bContainNull) {
   					resultStrArray[index++] = strCheck.substring(0, begin);
   				}
   				strCheck = strCheck.substring(end);
   				if (strCheck.length() == 0 && bContainNull) {
   					resultStrArray[index] = strCheck;
   					break;
   				}
   			}
   		}
   		return resultStrArray;
   	}

   	public static int search(String strTarget, String strSearch) {
   		int result = 0;
   		String strCheck = new String(strTarget);

   		for (int i = 0; i < strTarget.length();) {
   			int loc = strCheck.indexOf(strSearch);
   			if (loc == -1) {
   				break;
   			} else {
   				result++;
   				i = loc + strSearch.length();
   				strCheck = strCheck.substring(i);
   			}
   		}

   		return result;
   	}

   	public static String getTitleLimit(String title, int maxNum) {
   		int tLen = title.length();
   		int count = 0;
   		char c;
   		int s = 0;
   		for (s = 0; s < tLen; s++) {
   			c = title.charAt(s);
   			if (count > maxNum)
   				break;
   			if (c > 127)
   				count += 3;
   			else
   				count++;
   		}
   		return (tLen > s) ? title.substring(0, s) + "..." : title;
   	}

       /**
        * UTF-8 문자열을 파라미터 byte 기준으로 List 로 반환함
        * #70408 배송정책내용 확장
        *
        * @param source   the source
        * @param maxBytes the max bytes
        * @return the split array
        */
       public static List<String> getStringArray(String source, int maxBytes) {
           List<String> arr = new ArrayList();
           int current = 0, start = 0 , byteCnt = 0;
           try {
               Charset charset = Charset.forName("UTF-8");
               byte[] bytes = source.getBytes(charset);
               if ( bytes.length < maxBytes ) {
                   arr.add(source);
               } else {
                   while (current < source.length()) {
                       boolean constraint = false;
                       if ( 0x0000 <= source.charAt(current) && source.charAt(current) <= 0x007E ) {
                          byteCnt += 1;
                       } else { /*if (source.charAt(current) >= 0xAC00 && source.charAt(current) <= 0xD7AF) {*/
                           if ( byteCnt + 3 > maxBytes ) {
                               constraint = true; // 길이 초과 무시
                           } else {
                               source.charAt(current);
                               byteCnt += 3;
                           }
                       }
                       current += !constraint ? 1 : 0; // 초과건 다음 회차에 포함
                       if ( constraint || byteCnt >= maxBytes) {
                           arr.add( getUnitStr(bytes, start, byteCnt, charset));
                           // 시작 위치 조정 및 카운트 초기화
                           start += byteCnt;
                           byteCnt = 0;
                       }
                   }
                   if ( start <= bytes.length ) {
                       arr.add(getUnitStr(bytes, start, bytes.length - start, charset));
                   }
               }
           } catch ( Exception e) {
               e.printStackTrace();
           }
           return arr;
       }

       /**
        * byte 기준으로 추출하여 반환
        * #70408 배송정책내용 확장
        *
        * @param bytes   the bytes
        * @param start   the start
        * @param column  the column
        * @param charset the charset
        * @return the unit str
        */
       private static String getUnitStr(byte[] bytes, int start, int column, Charset charset) {
           CharsetDecoder decoder = charset.newDecoder();
           ByteBuffer bb = ByteBuffer.wrap(bytes, start , column );
           CharBuffer cb = CharBuffer.allocate(column);
           decoder.onMalformedInput(CodingErrorAction.IGNORE);
           decoder.decode(bb, cb, true);
           decoder.flush(cb);
           return new String(cb.array(), 0, cb.position());
       }

}
