package choi.choice.framework.systems.apps;

import choi.choice.framework.systems.SiteType;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(FrontPc.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface FrontPc {
    String PROFILE_NAME = "app_pc";

    String SYMBOL = "PC";

    String MALL_ID = "CHOICE";

    SiteType SITE_TYPE = SiteType.PcSite;
}
