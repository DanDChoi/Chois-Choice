package choi.choice.framework.json;

import choi.choice.framework.responsecode.FailedValidation;
import choi.choice.framework.responsecode.ResponseCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ValidationFailResponse extends JSendResponse {
    String message;
    String code;
    Map<String, Set<String>> data = new HashMap<>();

    public ValidationFailResponse(ResponseCode errorCode, List<FailedValidation> failedValidations) {
        status = JsendStatus.fail;
        message = errorCode.toRawMessage();
        code = errorCode.getCode();

        data = consolidateViolations(failedValidations);
    }

    public static Map<String, Set<String>> consolidateViolations(List<FailedValidation> failedValidations) {
        Map<String,Set<String>> data = new HashMap<>();
        for (FailedValidation failedValidation : failedValidations) {
            Set<String> errorTypes = data.get(failedValidation.getField());
            if (errorTypes == null) {
                errorTypes = new HashSet<>();
                data.put(failedValidation.getField(), errorTypes);
            }
            errorTypes.add(failedValidation.getErrorType());
        }
        return data;
    }
}
