package choi.choice.framework.utils;

public class CharSequenceUtils {
    static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
   			final CharSequence substring, final int start, final int length) {
   		if (cs instanceof String && substring instanceof String) {
   			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
   		}
   		int index1 = thisStart;
   		int index2 = start;
   		int tmpLen = length;

   		while (tmpLen-- > 0) {
   			char c1 = cs.charAt(index1++);
   			char c2 = substring.charAt(index2++);

   			if (c1 == c2) {
   				continue;
   			}

   			if (!ignoreCase) {
   				return false;
   			}

   			// The same check as in String.regionMatches():
   			if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
   					&& Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
   				return false;
   			}
   		}

   		return true;
   	}
}
