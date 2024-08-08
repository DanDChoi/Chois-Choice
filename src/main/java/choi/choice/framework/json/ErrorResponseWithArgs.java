package choi.choice.framework.json;

import choi.choice.framework.responsecode.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseWithArgs extends ErrorResponse {
    String code;
    Object data;

    public ErrorResponseWithArgs(ResponseCode errorCode) {
        super(errorCode.toMessage());
        code = errorCode.getCode();

    }

    public ErrorResponseWithArgs(ResponseException exception) {
        super(exception.getMessage());
        code = exception.getResponseCode().getCode();
    }

    public ErrorResponseWithArgs(String message, String code, Object data) {
        super(message);
        this.code = code;
        this.data = data;

    }

}
