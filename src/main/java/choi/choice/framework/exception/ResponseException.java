package choi.choice.framework.exception;

import choi.choice.framework.responsecode.ResponseCode;
import lombok.Getter;

/**
 * 예외 최상위 클래스
 */
public class ResponseException extends RuntimeException{

    private static final long serialVersionUID = -3464619227592080122L;

   	@Getter
   	protected ResponseCode responseCode;

   	public ResponseException() {
   		// Exception에서 super를 호출하지 않으면 stacktrace가 채워지지 않음. super를 호출해야
   		// 상위에서 fillStacktrace 를 호출하여 데이터를 채우게 됨
   		super();
   	}

   	public ResponseException(Throwable throwable) {
   		// 이것만 this를 호출하지 않고, super를 호출하는 이유는 상위클래스인 Throwable 에서 nested
   		// throwable의 toString을
   		// 호출하여 detailMessage를 보완하는 로직이 있기 때문임
   		super(throwable);
   	}

   	public ResponseException(String message) {
   		this(message, null, null);
   	}

   	public ResponseException(String message, Throwable throwable) {
   		this(message, null, throwable);
   	}

   	public ResponseException(ResponseCode responseCode) {
   		this(responseCode.toMessage(), responseCode, null);
   	}

   	public ResponseException(String message, ResponseCode responseCode, Throwable throwable) {
   		super(message, throwable);
   		this.responseCode = responseCode;
   	}

   	public ResponseException(Exception e) {
   		super(e);
   	}
}
