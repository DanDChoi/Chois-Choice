package choi.choice.framework.responsecode;

public interface ResponseCode {

    ResponseType getType();

    String getCode();

    BusinessType getBusinessType();

    String getCodeNo();

    String toMessage();

    String toMessage(Object... args);

    String toRawMessage();

    String toRawMessage(Object... args);

    String toUrlEncodedMessage();

    String toUrlEncodeMessage(Object... args);

    String etDefaultMessage();

    ResponseException toException();

    ResponseException toException(Object... args);

    ResponseException toException(Throwable throwable);

    ResponseException toException(Throwable throwable, Object... args);

}
