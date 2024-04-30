package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay_rfd")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayRfd {

    @Id
    @Column(name = "pay_no", unique = true)
    private String payNo;

    @Column(name = "rfd_turn")
    private int rfdTurn;

    @Column(name = "rfd_stat_cd")
    private String rfdStatCd;

    @Column(name = "rfd_requst_dt")
    private java.util.Date rfdRequstDt;

    @Column(name = "rfd_requst_resn")
    private String rfdRequstResn;

    @Column(name = "rfd_compt_dt")
    private java.util.Date rfdComptDt;

    @Column(name = "rfd_return_dt")
    private java.util.Date rfdReturnDt;

    @Column(name = "rfd_return_resn")
    private String rfdReturnResn;

    @Column(name = "rfd_cncl_dt")
    private java.util.Date rfdCnclDt;

    @Column(name = "rfd_cncl_resn")
    private String rfdCnclResn;

    @Column(name = "rfd_tp_cd")
    private String rfdTpCd;

    @Column(name = "rfd_crncy_cd")
    private String rfdCrncyCd;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
