package choi.choice.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class evt {

    @Id
    @Column(name = "evt_no", unique = true)
    private String evtNo;
    @Column(name = "evt_nm")
    private String evtNm;
    @Column(name = "evt_dscr")
    private String evtDscr;
    @Column(name = "evt_beg_dt")
    private java.util.Date evtBegDt;
    @Column(name = "evt_end_dt")
    private java.util.Date evtEndDt;
    @Column(name = "reply_use_yn")
    private String replyUseYn;
    @Column(name = "reply_config_sn")
    private String replyConfigSn;
}
