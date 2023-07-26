package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay {

    @Id
    @Column(name = "pay_no", unique = true)
    private String payNo;
    @Column(name = "pay_mn_cd")
    private String payMnCd;
    @Column(name = "pay_dt")
    private java.util.Date payDt;
    @Column(name = "sale_amt")
    private String payAmt;
    @Column(name = "ord_no")
    private String ordNo;
    @Column(name = "deal_tp_cd")
    private String dealTpCd;
    @Column(name = "dlv_cst_yn")
    private String dlvCstYn;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
