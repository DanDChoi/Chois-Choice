package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpn")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cpn {

    @Id
    @Column(name = "cpn_no", unique = true)
    private String cpnNo;
    @Column(name = "cpn_tp_cd")
    private String cpnTpCd;
    @Column(name = "cpn_dc_rate")
    private Integer cpnDcRate;
    @Column(name = "cpn_dc_amt")
    private Integer cpnDcAmt;
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
