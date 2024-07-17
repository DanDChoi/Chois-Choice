package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_blcklst")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrBlcklst {

    private String mallId;
    private String langCd;
    private String dvcCd;
    @Id
    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "blcklst_turn")
    private Integer blcklstTurn;

    @Column(name = "blcklst_beg_dt")
    private java.util.Date blcklstBegDt;

    @Column(name = "blcklst_end_dt")
    private java.util.Date blcklstEndDt;

    /**
     * 구분코드 등록: REG / 해제: RELIS
     */
    @Column(name = "blcklst_sect_cd")
    private String blcklstSectCd;

    @Column(name = "blcklst_resn_cont")
    private String blcklstResnCont;

    @Column(name = "blcklst_relis_resn_cont")
    private String blcklstRelisResnCont;

    /**
     * 유형 주문불가: ORD_IMPS / CS관리: CS_MANAGE
     */
    @Column(name = "blcklst_tp_cd")
    private String blcklstTpCd;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
