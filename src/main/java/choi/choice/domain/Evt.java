package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@Table(name = "evt")
public class Evt {

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
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
