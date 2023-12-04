package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clm_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClmGood {

    @Id
    @Column(name = "clm_no", unique = true)
    private String clmNo;
    @Column(name = "good_no")
    private String goodNo;
    @Column(name = "clm_good_turn")
    private int clmGoodTurn;
    @Column(name = "good_hist_no")
    private String goodHistNo;
    @Column(name = "good_nm")
    private String goodNm;
    @Column(name = "sale_prc")
    private String salePrc;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
