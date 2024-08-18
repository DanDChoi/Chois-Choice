package choi.choice.framework.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CdnUrlSerializer extends StdSerializer<String> implements ContextualSerializer {
    private static final long serialVersionUID = -1L;
    private ObjectMapper mapper;
    private boolean useCdnUrl;
    private CdnUrl.BucketType bucketType;

    public CdnUrlSerializer(ObjectMapper mapper, boolean useCdnUrl, CdnUrl.BucketType bucketType) {
        super(String.class);
        this.mapper = mapper;
        this.useCdnUrl = useCdnUrl;
        this.bucketType = bucketType;
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        CdnUrl cdnUrl = null;
        if (property == null) {
            return new CdnUrlSerializer(this.mapper, false, null);
        }

        cdnUrl = property.getAnnotation(CdnUrl.class);
        if (cdnUrl == null) {
            return this;
        }

        return new CdnUrlSerializer(this.mapper, true, cdnUrl.value());
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (!useCdnUrl) {
            gen.writeString(value);
            return;
        }

    }
}
