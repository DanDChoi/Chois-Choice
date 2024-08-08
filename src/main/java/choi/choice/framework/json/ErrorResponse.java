package choi.choice.framework.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ErrorResponse extends JSendResponse{
    String message;

   	public ErrorResponse(String message) {
   		status = JsendStatus.error;
   		this.message = message;

   	}
}
