package choi.choice.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * json 마샬링할 경 Object가 null이면 ""처리함
 */
public class NullSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        jgen.writeString("");
    }
}
