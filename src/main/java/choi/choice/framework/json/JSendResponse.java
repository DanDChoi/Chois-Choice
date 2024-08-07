package choi.choice.framework.json;

import choi.choice.framework.utils.SecureCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
public class JSendResponse {

    JsendStatus status;

    protected JSendResponse(JsendStatus status) {this.status = status;}

    public static ResponseEntity<? extends JSendResponse> success() {return ResponseEntity.ok(new SuccessResponse());}

    public static ResponseEntity<? extends JSendResponse> success(Object data) {
        return ResponseEntity.ok(new SuccessResponseWithData(data));
    }

    public static ResponseEntity<? extends JSendResponse> fail(Object data) {
        return ResponseEntity.badRequest().body(new FailResponseWithData(data));
    }

    public static ResponseEntity<? extends JSendResponse> error(String message, String code, Object data) {
        /**
         * XSS
         */
        message = SecureCode.xssReplaceAll(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseWithArgs(message, code, data));
    }

    public static ResponseEntity<? extends JSendResponse> error(String message) {
        /**
         * XSS
         */
        message = SecureCode.xssReplaceAll(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(message));
    }
}
