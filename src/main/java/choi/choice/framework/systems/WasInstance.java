package choi.choice.framework.systems;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;

import java.util.regex.Pattern;

public enum WasInstance {
    M1(1), M2(2), M3(3), M4(4), M5(5), M6(6), M7(7), M8(8), M9(9), M10(10) , M11(11) , M12(12);

   	static final String WAS_INSTANCE = "wasInstance";

   	@Getter
   	String symbol;

   	@Getter
   	int no;

   	WasInstance(int no) {
   		this.no = no;
   		symbol = Integer.toHexString(ordinal() + 1).toUpperCase();
   	}

   	private static Pattern pattern = Pattern.compile("^.*m(\\d+)$");

   	public static WasInstance decideWasInstance(Environment env) {
   		if (StringUtils.isNotEmpty(env.getProperty(WAS_INSTANCE))) {
   			return handleSystemProperty(env);
   		}
   		return M1;

   	}

   	private static WasInstance handleSystemProperty(Environment env) {
   		String noStr = env.getProperty(WAS_INSTANCE);
   		int no = Integer.parseInt(noStr);
   		return WasInstance.values()[no - 1];
   	}
}
