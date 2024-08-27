package choi.choice.framework.enums;

public enum InterfaceStatusEnum {

    INTERFACE_RESULT_SUCCESS("SUCCESS"),
   	INTERFACE_RESULT_FAIL("FAIL"),
   	INTERFACE_ELAPSE_RESET("0.000"),
   	INTERFACE_SERVER_FAIL("INTERFACE_SERVER_FAIL"),
   	INTERFACE_NA("N/A"),
   	INTERFACE_RESULT_SUCCESS_CODE("0000"),
   	INTERFACE_RESULT_FAIL_CODE("99"),
   	INTERFACE_TOPT_RESULT_SUCCESS_CODE("200"),
   	INTERFACE_TOPT_RESULT_FAIL_CODE("500"),
   	INTERFACE_PREFIX("[INF]"),
   	INTERFACE_FO("[FO]"),
   	;

   	public String statusMessage;

   	private InterfaceStatusEnum(String statusMessage) {
   		this.statusMessage = statusMessage;
   	}

   	@Override
   	public String toString() {
   		return statusMessage;
   	}
}
