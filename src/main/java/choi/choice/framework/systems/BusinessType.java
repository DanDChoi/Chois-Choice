package choi.choice.framework.systems;

import lombok.Getter;

public enum BusinessType {
    AFFILIATE("AF", "goods/affiliate", "제휴"),
    BRAND("BR", "brand", "브랜드")
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
