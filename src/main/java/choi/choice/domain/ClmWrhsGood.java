package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clm_wrhs_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClmWrhsGood {

    @EmbeddedId
    private ClmWrhsGoodPK clmWrhsGoodPK;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "ord_good_turn")
    private int ordGoodTurn;

    @Column(name = "dlv_pcupsp_turn")
    private int dlvPcupspTurn;

    @Column(name = "clm_resn_cd")
    private String clmResnCd;

    @Column(name = "clm_resn_cont")
    private String clmResnCont;

    @Column(name = "good_no")
    private String goodNo;

    @Column(name = "itm_no")
    private String itmNo;

    @Column(name = "good_hist_turn")
    private int goodHistTurn;

    @Column(name = "itm_hist_turn")
    private int itmHistTurn;

    @Column(name = "good_nm")
    private String goodNm;

    @Column(name = "itm_nm")
    private String itmNm;

    @Column(name = "com_id")
    private String comId;

    @Column(name = "dlv_plc_no")
    private int dlvPlcNo;

    @Column(name = "clm_qty")
    private int clmQty;

    @Column(name = "sale_prc")
    private String salePrc;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
