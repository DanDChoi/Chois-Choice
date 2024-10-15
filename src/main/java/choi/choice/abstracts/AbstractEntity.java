package choi.choice.abstracts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public abstract class AbstractEntity implements Serializable, Cloneable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2933453273621043676L;

    /**
     * 페이지 시작 row 번호.
     */
    private int beginIndex = 0;

    /**
     * 페이지 마지막 row 번호.
     */
    private int endIndex = 0;

    /* 몰 아이디 */
    private String mallId;

    /* 언어코드 */
    private String lang;

    /* 접속 채널 */
    private String channel;

    /* 접속 디바이스 */
    private String device;

    /* 마스킹 여부 */
    private String maskingYn;

    /* 메뉴 일련번호 */
    Long accessMenuSn;

    /* 로그인 ID */
    private String loginId;

    private String userTrackingId;
}
