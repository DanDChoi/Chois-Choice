package choi.choice.result;

import choi.choice.framework.enums.InterfaceStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CommonResult implements Serializable {
    private static final long serialVersionUID = 9051756706730640947L;

    @JsonProperty("CODE")
    @ApiModelProperty(value = "응답코드", example = "0000")
    private String code = InterfaceStatusEnum.INTERFACE_RESULT_SUCCESS_CODE.toString();

    @JsonProperty("MESSAGE")
    @ApiModelProperty(name = "응답메시지", example = "SUCCESS")
    private String message = InterfaceStatusEnum.INTERFACE_RESULT_SUCCESS.toString();
}
