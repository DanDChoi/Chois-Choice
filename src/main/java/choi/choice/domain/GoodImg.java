package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good_img")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodImg {

    @Id
    @Column(name = "good_no")
    private String goodNo;

    /**
     * THNAIL : 섬네일 / GOOD_MODEL_INFO : 상품모델정보
     */
    @Column(name = "img_tp_cd")
    private String imgTpCd;

    /**
     * ORGINL : 원본
     */
    @Column(name = "img_size_cd")
    private String imgSizeCd;

    @Column(name = "img_turn")
    private Integer imgTurn;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "rprst_img_yn")
    private String rprstImgYn;

    @Column(name = "model_img_yn")
    private String modelImgYn;

    @Column(name = "img_dscr")
    private String imgDscr;

    @Column(name = "img_aprv_yn")
    private String imgAprvYn;

    @Column(name = "img_use_yn")
    private String imgUseYn;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
