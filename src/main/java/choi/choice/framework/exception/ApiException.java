package choi.choice.framework.exception;

import lombok.Data;

/**
 * for printing error messages from interface server when handling api response errors
 */

@Data
public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;

    private int statusCode;

    public ApiException(int statusCode, String message) {
        super(message);
        if (statusCode < 900) {
            throw new IllegalArgumentException("Status Code must be greater than 900");
        }
        this.statusCode = statusCode;
    }
}
