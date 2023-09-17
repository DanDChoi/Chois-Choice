package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cso_mtm_inquiry")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CsoMtmInquiry {

    @Id
    @Column(name = "mtm_sn", unique = true)
    private String mtmSn;
    @Column(name = "good_no")
    private String good_no;
    @Column(name = "mbr_no")
    private Long mbrNo;
    @Column(name = "inquiry_add_dt")
    private java.util.Date inquiryAddDt;
    @Column(name = "mtm_cont")
    private String mtmCont;
    @Column(name = "mtm_answer_yn")
    private String mtmAnswerYn;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
