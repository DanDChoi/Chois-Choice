package choi.choice.domain;

import lombok.*;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_bnef")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrBnef {

    @Id
    @Column(name = "bnef_sn")
    private int bnefSn;

    /**
     * 등급 : GRD
     * 생일 : BRTH
     */
    @Column(name = "bnef_sect_cd")
    private String bnefSectCd;

    @Column(name = "bnef_detail_sect_cd")
    private String bnefDetailSectCd;

    @Column(name = "bnef_nm")
    private String bnefNm;

    @Column(name = "bnef_beg_dt")
    private java.util.Date bnefBegDt;

    @Column(name = "bnef_end_dt")
    private java.util.Date bnefEndDt;

    /**
     * 승인구분
     * APRV_WAIT / APRV / STPGE
     */
    @Column(name = "aprv_sect_cd")
    private String aprvSectCd;

    @Column(name = "aprv_admin_id")
    private String aprvAdminId;

    @Column(name = "aprv_dt")
    private java.util.Date aprvDt;

    @Column(name = "stpge_admin_id")
    private String stpgeAdminId;

    @Column(name = "stpge_dt")
    private java.util.Date stpgeDt;

    @Column(name = "stpge_resn_cont")
    private String stpgeResnCont;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

}
