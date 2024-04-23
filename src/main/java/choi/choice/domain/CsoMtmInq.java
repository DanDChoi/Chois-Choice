package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cso_mtm_inq")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CsoMtmInq {

    @Id
    @Column(name = "mtm_sn", unique = true)
    private String mtmSn;

    @Column(name = "inq_tp_cd") //문의유형코드
    private String inqTpCd;

    @Column(name = "good_no")
    private String good_no;

    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "mbr_nm")
    private String mbrNm;

    @Column(name = "inq_dt")
    private java.util.Date inqDt;

    @Column(name = "inq_cont")
    private String inqCont;

    @Column(name = "ans_stat_cd")
    private String ansStatCd;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
