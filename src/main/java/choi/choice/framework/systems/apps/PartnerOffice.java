package choi.choice.framework.systems.apps;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Profile(PartnerOffice.PROFILE_NAME)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface PartnerOffice {
	String PROFILE_NAME = "app_bo";

	String SYMBOL = "PO";
}