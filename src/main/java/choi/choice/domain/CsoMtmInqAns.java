package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cso_mtm_inq_ans")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CsoMtmInqAns {

    @Id
    @Column(name = "mtm_sn")
    private String mtmSn;

    @Column(name = "mtm_inq_ans_turn")
    private int mtmInqAnsTurn;

    @Column(name = "ans_sj")
    private String ansSj;

    @Column(name = "ans_cont")
    private String ansCont;

    @Column(name = "detail_ans_stat_cd")
    private String detailAnsStatCd;

    @Column(name = "ans_cnfirm_yn")
    private String ansCnfirmYn;

    @Column(name = "ans_dt")
    private java.util.Date ansDt;

    @Column(name = "ans_admin_id")
    private String andAdminId;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
