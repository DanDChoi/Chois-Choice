package choi.choice.framework.commons;

import choi.choice.framework.cloud.aws.S3Bucket;
import choi.choice.framework.spring.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Slf4j
public class ConfigService {

    @Autowired
   	private Environment env;

   	/* key값에 해당하는 properties 값을 리턴 한다. */
   	public String getProperty(String key) {

   		if (env == null) {
   			log.warn("Environment object is null");
   			return null;
   		}
   		return env.getProperty(key);
   	}

   	public String getTempPath(S3Bucket s3Bucket) {
           Environment environment = ApplicationContextProvider.getContext().getEnvironment();
           String temporary = "/temp";
           if ( S3Bucket.PRODUCTS.equals(s3Bucket)) {
   			return environment.getProperty("base.s3.product.path") + temporary;
   		} else if ( S3Bucket.CONTENTS.equals(s3Bucket)) {
   			return environment.getProperty("base.s3.display.path") + temporary;
           } else if ( S3Bucket.PRIVATE_ATTACH.equals(s3Bucket)) {
               return environment.getProperty("base.s3.private.attach.path") + temporary;
           }
           return environment.getProperty("base.s3.public.attach.path") + temporary;
       }
}
