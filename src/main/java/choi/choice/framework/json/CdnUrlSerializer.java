package choi.choice.framework.json;

import choi.choice.framework.data.mobile.LiteDeviceResolver;
import choi.choice.framework.data.mobile.MobileDevice;
import choi.choice.framework.spring.ApplicationContextProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

        ApplicationContext ctx = ApplicationContextProvider.getContext();
        Environment env = ctx.getEnvironment();
        LiteDeviceResolver ldr = ctx.getBean(LiteDeviceResolver.class);

        if (CdnUrl.BucketType.IMG_CONTENT == this.bucketType) {
            value = checkImgType(value, ldr);
            gen.writeString(env.getProperty("url.content") + value);
        } else if (CdnUrl.BucketType.IMG_PRODUCT == this.bucketType) {
            value = checkImgType(value, ldr);
            gen.writeString(env.getProperty("url.image") + value);
        } else if (CdnUrl.BucketType.ATTACH_PUBLIC == this.bucketType) {
            gen.writeString(env.getProperty("url.attach.public") + value);
        } else if (CdnUrl.BucketType.ATTACH_PRIVATE == this.bucketType) {
            gen.writeString(value);
        } else if (CdnUrl.BucketType.MEDIA_PUBLIC == this.bucketType) {
            String s3Name = "s3://" + env.getProperty("base.s3.media.convert.products.bucket");
            String httpsName = "http://" + env.getProperty("base.mediaconvert.domain");
            if (value.startsWith(s3Name)) {
                String path = value.substring(s3Name.length());
                gen.writeString(httpsName + path);
            } else {
                gen.writeString(value);
            }
        } else {
            log.warn("invalid CdnUrl.BucketType:{}, print nothing", this.bucketType);
        }

    }

    private String checkImgType(String path, LiteDeviceResolver ldr) {
        boolean useWebp = false;

        if (path == null || path.length() == 0) {
            return path;
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

            String userAgent = StringUtils.defaultString(request.getHeader("User-Agent"), "").toLowerCase();
            String httpAccept = StringUtils.defaultString(request.getHeader("Accept"), "").toLowerCase();

            if (httpAccept.contains("image/webp")) {
                useWebp = true;
            } else {
                MobileDevice device = ldr.resolveDevice(request);
                if (device.isMobile()) {
                    useWebp = true;
                }

                if (!device.isMobile() && StringUtils.isNotEmpty(userAgent) && userAgent.indexOf("chrome") != -1) {
                    useWebp = true;
                }
            }
        }

        if (useWebp) {
            if (path.endsWith(".jpg") || path.endsWith(".png")) {
                return path.substring(0, (path.length() -4)) + ".webp";
            }
        }

        return path;
    }
}
