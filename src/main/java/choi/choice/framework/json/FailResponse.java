package choi.choice.framework.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class FailResponse extends JSendResponse{
    public FailResponse() {
   		status = JsendStatus.fail;
   	}
}

@Getter
@Setter
class FailResponseWithData extends FailResponse {
    private Object data;

    FailResponseWithData(Object data) {
        this.data = data;
    }
}
