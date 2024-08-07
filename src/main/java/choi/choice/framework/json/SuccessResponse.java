package choi.choice.framework.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse extends JSendResponse{
    public SuccessResponse() {
   		super(JsendStatus.success);
   	}
}

@Getter
@Setter
class SuccessResponseWithData extends SuccessResponse{
	private Object data;

	SuccessResponseWithData(Object data){
		this.data = data;

	}
}

