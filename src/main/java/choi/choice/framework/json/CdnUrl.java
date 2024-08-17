package choi.choice.framework.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CdnUrl {
    enum BucketType {
        IMG_PRODUCT,
        IMG_CONTENT,
        ATTACH_PUBLIC,
        ATTACH_PRIVATE,
        MEDIA_PUBLIC,
    }

    BucketType value();
}
