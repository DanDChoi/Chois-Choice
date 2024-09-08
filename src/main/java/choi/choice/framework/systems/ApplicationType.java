package choi.choice.framework.systems;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.Environment;

@Slf4j
public class ApplicationType {

    FONT_PC (FrontPc.SYMBOL, FrontPc.PROFILE_NAME, FrontPc.MALL_ID, FrontPc.SITE_TYPE);


    @Getter
    private String mallId;

    @Getter
    private String symbol;

    @Getter
    private String systemId;

    @Getter
    private String siteType;

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
        String systemId = env.getProperty("system_id");
        ApplicationType appType = ApplicationType.findBySystemId(systemId);

        if (appType == ApplicationType.)
    }
}
