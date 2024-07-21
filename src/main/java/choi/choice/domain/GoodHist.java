package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "good_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodHist {

    private String mallId;
    private String langCd;
    private String dvcCd;

    @EmbeddedId
    private GoodHistPK goodHistPK;
    @Column(name = "good_nm")
    private String goodNm;
    @Column(name = "hist_dt")
    private java.util.Date histDt;
    @Column(name = "good_prc")
    private String goodPrc;
    @Column(name = "std_ctgry_no")
    private String stdCtgryNo;
    @Column(name = "sale_beg_date")
    private String saleBegDate;
    @Column(name = "sale_end_date")
    private String saleEndDate;
    @Column(name = "dlv_plc_no")
    private Integer dlvPlcNo;
    @Column(name = "color_nm")
    private String colorNm;
    @Column(name = "color_cd")
    private String colorCd;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
