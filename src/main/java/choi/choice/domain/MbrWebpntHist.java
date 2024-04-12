package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_webpnt_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrWebpntHist {

    @Id
    @Column(name = "webpnt_sn")
    private Long webpnt_sn;

    @Column(name = "upper_webpnt_sn")
    private Long upper_webpnt_sn;

    @Column(name = "mbr_no")
    private Long mbr_no;

    @Column(name = "webpnt_tp_cd")
    private String webpntTpCd;

    @Column(name = "webpnt_resn_cd")
    private String webpntResnCd;

    @Column(name = "webpnt_detail_resn_cd")
    private String webpntDetailResnCd;

    @Column(name = "webpnt_amt")
    private java.math.BigDecimal webpntAmt;

    @Column(name = "remndr_webpnt_amt")
    private java.math.BigDecimal remndrWebpntAmt;

    @Column(name = "occur_dt")
    private java.util.Date occurDt;

    @Column(name = "use_beg_dt")
    private java.util.Date useBegDt;

    @Column(name = "use_end_dt")
    private java.util.Date useEndDt;

    @Column(name = "cncl_dt")
    private java.util.Date cnclDt;

    @Column(name = "resn_dscr")
    private String resnDscr;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "ord_good_turn")
    private int ordGoodTurn;

    @Column(name = "clm_no")
    private String clmNo;

    @Column(name = "clm_good_turn")
    private int clmGoodTurn;

    @Column(name = "evt_no")
    private String evtNo;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
