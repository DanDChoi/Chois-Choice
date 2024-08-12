package choi.choice.framework.systems;

import lombok.Getter;

public enum BusinessType {
    AFFILIATE 		    ("AF",  	"goods/affiliate",				"제휴"),
    BRAND 			    ("BR",  	"brand",						"브랜드"),
    CALLCENTER 		    ("CC",  	"callcenter",					"고객센터"),
    CART 			    ("CT",  	"cart",						"장바구니"),
    CLAIM 			    ("CL",  	"claim",						"클레임"),
    COMMONS 		    ("SC",  	"commons",						"시스템공통"),
    COUPON 			    ("CP",  	"promotion/coupon",			"쿠폰"),
    CUSTOMERSERVICE     ("CS",  	"callcenter/customerservice",	"상담관리"),
    DELIVERY 		    ("DL",  	"delivery",					"배송"),
    DISPLAY 		    ("DP",  	"display",						"전시"),
    EVENTS 			    ("ET",  	"promotion/events",			"이벤트"),
    FAQ 			    ("FQ",  	"callcenter/faq",				"FAQ"),
    GOODS 			    ("GD",  	"goods",						"상품"),
    GUEST 			    ("GT",  	"guest",						"비회원"),
    INTRO 			    ("MI",  	"intro",						"메인"),
    MAGAZINE 		    ("MZ",  	"promotion/magazine",			"매거진"),
    MEMBER 			    ("MB",  	"member",						"회원"),
    MYPAGE 			    ("MP",  	"mypage",						"마이페이지"),
    ORDER 			    ("OD",  	"order",						"주문"),
    PAYMENT 		    ("PY",  	"payment",						"결제"),
    PROMOTION 		    ("PM",  	"promotion",					"프로모션"),
    SEARCH 			    ("SH",  	"search",						"검색"),
    SETTLEMENT 		    ("SM",  	"settlement",					"매출및정산"),
    STAFF 			    ("ST",  	"mmber/staff",					"임직원"),
    VENDOR 			    ("VD",  	"vendor",						"업체"),
    ELECTRONICCONTRACT  ("EC",	"electronic/contract",			"전자계약")
    ;

    @Getter
    private String code;

    @Getter
    private String directory;

    @Getter
    private String description;

    BusinessType(String code, String directory, String description) {
        this.code = code;
        this.directory = directory;
        this.description = description;
    }

    public static boolean contains(String code) {
        for(BusinessType type : values()){
            if (type.code.equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static BusinessType codeOf(String code) {
        for(BusinessType type : values()){
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
