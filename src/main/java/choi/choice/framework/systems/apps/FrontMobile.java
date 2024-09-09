package choi.choice.framework.systems.apps;

import choi.choice.framework.systems.SiteType;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(FrontMobile.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface FrontMobile {
	String PROFILE_NAME = "app_mb";

	String SYMBOL = "MB";

	String MALL_ID = "WWM";

	SiteType SITE_TYPE = SiteType.MobileSite;
}