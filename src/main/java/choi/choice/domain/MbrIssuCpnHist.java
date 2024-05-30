package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mbr_issu_cpn_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrIssuCpnHist {

    @EmbeddedId
    private MbrIssuCpnHistPK mbrIssuCpnHistPK;

    @Column(name = "mbr_no")
    private String mbrNo;

    @Column(name = "cpn_tp_cd")
    private String cpnTpCd;

    @Column(name = "cpn_stat_cd")
    private String cpnStatCd;

    @Column(name = "cpn_publi_dt")
    private java.util.Date cpnPubliDt;

    @Column(name = "valid_beg_dt")
    private String validBegDt;

    @Column(name = "valid_end_dt")
    private String validEndDt;

    @Column(name = "valid_beg_hour")
    private String validBegHour;

    @Column(name = "valid_end_hour")
    private String validEndHour;

    @Column(name = "cpn_use_dt")
    private java.util.Date cpnUseDt;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "clm_no")
    private String clmNo;

    @Column(name = "prm_no")
    private String prmNo;

    @Column(name = "issu_admin_id")
    private String issuAdminId;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
