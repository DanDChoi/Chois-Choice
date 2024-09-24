package choi.choice.framework.data;

import lombok.Data;

@Data
public class SercurityJsonResult {
    /* 리다이렉트 URL */
    String loginTarget;

    String token;

    String failMessage;
    String sucessMessage;

    String loginTp;  // GTM을 위한 로그인 유형 (일반,네이버,카카오,애플,구글 로그인)
    String code;

    String ssoMcustNo; // SSO 인증을 위한 암호화된 HPoint 통합 멤버십 고객 번호

    String ssoAuthCd; // SSO 인증을 위한 암호화된 HPoint 통합 멤버십 인증코드

    String drmcMbrNo; // 휴면 회원번호
}
