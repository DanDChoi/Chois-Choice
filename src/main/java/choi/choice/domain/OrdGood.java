package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ord_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrdGood {

    @EmbeddedId
    private OrdGoodPK ordGoodPK;

    @Column(name = "good_no")
    private String goodNo;

    @Column(name = "good_hist_no")
    private String goodHistNo;

    @Column(name = "good_nm")
    private String goodNm;

    @Column(name = "itm_no")
    private String itmNo;

    @Column(name = "itm_hist_no")
    private Integer itmHistNo;

    @Column(name = "std_ctgry_no")
    private String stdCtgryNo;

    @Column(name = "sale_prc")
    private String salePrc;

    @Column(name = "dlv_plc_no")
    private Integer dlvPlcNo;

    @Column(name = "mcode")
    private String mcode;

    @Column(name = "mcode_detail")
    private String mcodeDetail;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
