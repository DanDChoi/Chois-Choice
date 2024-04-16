package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good_tag_resve")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodTagResve {
    //상품 태그 예약
    @Id
    @Column(name = "tag_resve_sn", unique = true)
    private Long tagResveSn;

    @Column(name = "tag_nm")
    private String tagNm;

    @Column(name = "resve_beg_dt")
    private java.util.Date resveBegDt;

    @Column(name = "resve_end_dt")
    private java.util.Date resveEndDt;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
