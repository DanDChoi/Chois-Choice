package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lgs_rtrvl_drct_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LgsRtrvlDrctGood {

    @Id
    @Column(name = "rtrvl_drct_good_no")
    private String rtrvlDrctGoodNo;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "clm_no")
    private String clmNo;

    @Column(name = "clm_wrhs_good_turn")
    private Integer clmWrhsGoodTurn;

    @Column(name = "dlivy_drct_good_no")
    private String dlivyDrctGoodNo;

    @Column(name = "dlv_pcupsp_turn")
    private Integer dlvPcupspTurn;

    @Column(name = "dlv_turn")
    private Integer dlvTurn;

    @Column(name = "rtrvl_drct_tgt_yn")
    private String rtrvlDrctTgtYn;

    @Column(name = "rtrvl_drct_yn")
    private String rtrvlDrctYn;

    @Column(name = "rtrvl_qty")
    private int rtrvlQty;

    @Column(name = "rtrvl_drct_tp_cd")
    private String rtrvlDrctTpCd;

    @Column(name = "rtrvl_drct_dt")
    private java.util.Date rtrvlDrctDt;

    @Column(name = "rtrvl_drct_qty")
    private int rtrvlDrctQty;

    @Column(name = "rtrvl_drct_wthdraw_qty")
    private int rtrvlDrctWthDrawQty;

    @Column(name = "rtrvl_drct_wthdraw_dt")
    private java.util.Date rtrvlDrctWthDrawDt;

    @Column(name = "wrhs_compt_dt")
    private java.util.Date wrhsComptDt;

    @Column(name = "rtrvl_compt_dt")
    private java.util.Date rtrvlComptDt;

    @Column(name = "rtrvl_good_stat_cd")
    private String rtrvlGoodStatCd;

    @Column(name = "rtrvl_good_prcs_compt_yn")
    private String rtrvlGoodPrcsComptYn;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;
}
