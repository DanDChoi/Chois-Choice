package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpn_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CpnHist {

    @Id
    @Column(name = "cpn_no", unique = true)
    private String cpnNo;
    @Id
    @Column(name = "cpn_hist_turn", unique = true)
    private int cpnHistTurn;
    @Id
    @Column(name = "hist_end_dt", unique = true)
    private java.util.Date histEndDt;
    @Column(name = "hist_beg_dt")
    private java.util.Date histBegDt;
    @Column(name = "cpn_tp_cd")
    private String cpnTpCd;
    @Column(name = "cpn_dc_rate")
    private int cpnDcRate;
    @Column(name = "cpn_dc_amt")
    private int cpnDcAmt;
    @Column(name = "cpn_beg_dt")
    private java.util.Date cpnBegDt;
    @Column(name = "cpn_end_dt")
    private java.util.Date cpnEndDt;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
