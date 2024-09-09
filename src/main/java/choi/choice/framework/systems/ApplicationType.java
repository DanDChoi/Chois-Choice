package choi.choice.framework.systems;

import choi.choice.framework.systems.apps.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

@Slf4j
public class ApplicationType {

    FRONT_PC			  (FrontPc.SYMBOL, FrontPc.PROFILE_NAME, FrontPc.MALL_ID, FrontPc.SITE_TYPE),
    FRONT_MOBILE		  (FrontMobile.SYMBOL, FrontMobile.PROFILE_NAME, FrontMobile.MALL_ID, FrontMobile.SITE_TYPE),
    BACK_OFFICE     	      (BackOffice.SYMBOL, BackOffice.PROFILE_NAME),
//    PARTNER_OFFICE		      (PartnerOffice.SYMBOL, 			PartnerOffice.PROFILE_NAME),
//    CUSTOMER_SERVICE          (CustomerService.SYMBOL, 			CustomerService.PROFILE_NAME),
    INTERFACES			      (InterfaceServer.SYMBOL, InterfaceServer.PROFILE_NAME),
    API			      		  (APIServer.SYMBOL, APIServer.PROFILE_NAME),
    BATCH				      (Batch.SYMBOL, 					Batch.PROFILE_NAME),
    UNKNOWN				      ("UN", 				"UNKNOWN");


    @Getter
    private String mallId;

    @Getter
    private String symbol;

    @Getter
    private String systemId;

    @Getter
    private SiteType siteType;

    ApplicationType(String twoLetterSymbol, String systemId) {
        this(twoLetterSymbol, systemId, null, SiteType.Internal);
    }

    ApplicationType(String twoLetterSymbol, String systemId, String mallId, SiteType siteType) {
        this.symbol = twoLetterSymbol;
        this.systemId = systemId;
        this.mallId = mallId;
        this.siteType = siteType;
    }

    static ApplicationType findBySystemId(String systemId) {
        for (ApplicationType type : values()){
            if (type.getSystemId().equals(systemId)) {
                return type;
            }
        }
        return ApplicationType.UNKNOWN;
    }

    public static ApplicationType decideApplicationType(Environment env) {
        String systemId = env.getProperty("system.id");
        ApplicationType appType = ApplicationType.findBySystemId(systemId);

//        if (appType == ApplicationType.BACK_OFFICE && "po".equals(env.getProperty("boenvtype"))) {
//            appType = ApplicationType.PARTNER_OFFICE;
//        }
        log.info("Application Type: {}", appType);
        return appType;
    }
}
