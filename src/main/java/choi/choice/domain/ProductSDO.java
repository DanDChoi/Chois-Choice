package choi.choice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(value = "상품 SDO")
@Alias("productSDO")
public class ProductSDO implements Serializable {
    private static final long serialVersionUID = -196396732004154153L;

    @JsonProperty("GOOD_NM")
    @ApiModelProperty(name = "상품명", example = "상품명", required = true)
    private String goodNm;

    @JsonProperty("GOOD_TP")
    @ApiModelProperty( name = "상품유형", example = "GNRL_GOOD", required=true)
    private String goodTp;

    @JsonProperty("NTFC_MNFCTUR_NATION")
    @ApiModelProperty( name = "제조국(원산지)", example = "대한민국", required=true)
    private String ntfcMnfcturNation;

    @JsonProperty("NTFC_MNFCTUR_YM")
    @ApiModelProperty( name = "제조년월", example = "201308", required=true)
    private String ntfcMnfcturYm;

    @JsonProperty("NTFC_PRD_PCL_MATR")
    @ApiModelProperty( name = "소재", example = "겉감:소가죽", required=true)
    private String ntfcPrdPclMatr;

    @JsonProperty("NTFC_MAKER")
    @ApiModelProperty( name = "제조자", example = "제조자", required=true)
    private String ntfcMaker;

    @JsonProperty("MNFCT_TP_CD")
    @ApiModelProperty( name = "제작유형", example = "ORDMK", required=true)
    private String mnfctTpCd;

    @JsonProperty("STD_CTGRY_NO")
    @ApiModelProperty( name = "표준카테고리 ID", example = "A01A01", required=true)
    private String stdCtgryNo;

    @JsonProperty("SALE_DETAIL_TP_CD")
    @ApiModelProperty( name = "판매유형", example = "GNRL", required=true)
    private String saleDetailTpCd;

    @JsonProperty("CVR_PRC")
    @ApiModelProperty( name = "정상판매가", example = "50000", required=true)
    private java.math.BigDecimal cvrPrc;
}
