package choi.choice.framework.systems;

import lombok.Getter;

public enum ResidenceArea {
    강원도("42000"),
    경기도("41000"),
   	경상남도("48000"),
   	경상북도("47000"),
   	광주광역시("29000"),
   	대구광역시("27000"),
   	대전광역시("30000"),
   	부산광역시("26000"),
   	서울특별시("11000"),
   	울산광역시("31000"),
   	인천광역시("28000"),
   	전라남도("46000"),
   	전라북도("45000"),
   	제주특별자치도("50000"),
   	충청남도("44000"),
   	충청북도("43000");

    @Getter
    private String code;

    ResidenceArea(String code) {this.code = code;}

    public static ResidenceArea of(String code) {
        for (ResidenceArea area : values()) {
            if (area.getCode().equals(code)) {
                return area;
            }
        }
        return null;
    }
}
