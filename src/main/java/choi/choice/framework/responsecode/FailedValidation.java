package choi.choice.framework.responsecode;

import lombok.Data;

import java.io.Serializable;

/**
 * 실패한 검증에 대한 DTO 클래스
 */
@Data
public class FailedValidation implements Serializable {
    private static final long serialVersionUID = -6176853450902230783L;

   	String field;
   	String errorType;

   	public FailedValidation(String errorType, String field) {
   		super();
   		this.errorType = errorType;
   		this.field = field;
   	}
}
