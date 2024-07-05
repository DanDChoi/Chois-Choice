package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clm")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clm {

    @Id
    @Column(name = "clm_no", unique = true)
    private String clmNo;

    /**
     * 클레임 유형 코드
     * EXCHG
     * REFND
     */
    @Column(name = "clm_tp_cd")
    private String clmTpCd;

    /**
     * 클레임 상태 코드
     * CLM_RECPT
     * CLM_COMPT
     */
    @Column(name = "clm_stat_cd")
    private String clmStatCd;

    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "good_no")
    private String goodNo;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

}
