package choi.choice.framework.json;

import com.fasterxml.jackson.databind.*;

public class CdnUssrSerializer {
    private static final long serialVersionUID = -1L;
    private ObjectMapper mapper;
    private boolean useCdnUrl;
    private CdnUrl.BucketType bucketType;

    public CdnUssrSerializer(ObjectMapper mapper, boolean useCdnUrl, CdnUrl.BucketType bucketType) {
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
}
