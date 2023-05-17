package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Good {

    @Id
    @Column(name = "good_no", unique = true)
    private String goodNo;
    @Column(name = "good_nm")
    private String goodNm;
    @Column(name = "sale_beg_date")
    private String saleBegDate;
    @Column(name = "sale_end_date")
    private String saleEndDate;
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
