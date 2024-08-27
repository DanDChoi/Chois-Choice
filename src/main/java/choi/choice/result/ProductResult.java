package choi.choice.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("productResult")
@NoArgsConstructor
public class ProductResult extends CommonResult implements Serializable {

    private static final long serialVersionUID = -7921634883716589201L;

    @JsonProperty("GOOD_NO")
    @ApiModelProperty( name = "상품번호", example = "958473633")
    private String godNo;

    @JsonProperty("COM_GOOD_NO")
    @ApiModelProperty( name = "판매자 상품번호", example = "1234567")
    private String comGodNo;
}
