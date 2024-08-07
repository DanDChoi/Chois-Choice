package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "std_ctgry")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StdCtgry {

    @Id
    @Column(name = "std_ctgry_no", unique = true)
    private String stdCtgryNo;

    @Column(name = "upper_std_ctgry_no")
    private String upperStdCtgryNo;

    @Column(name = "std_ctgry_nm")
    private String stdCtgryNm;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "delete_yn")
    private String deleteYn;

    @Column(name = "leaf_ctgry_yn")
    private String leafCtgryYn;

    @Column(name = "prdlst_grp_cd")
    private String prdlstGrpCd;

    @Column(name = "good_evl_grp_cd")
    private String goodEvlGrpCd;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;

}
