package choi.choice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class pay {

    @Id
    @Column(name = "pay_no", unique = true)
    private String payNo;
    @Column(name = "pay_mn_cd")
    private String payMnCd;
    @Column(name = "pay_dt")
    private String payDt;
    @Column(name = "ord_no")
    private String ordNo;
    @Column(name = "deal_tp_cd")
    private String dealTpCd;
    @Column(name = "dlv_cst_yn")
    private String dlvCstYn;
}
