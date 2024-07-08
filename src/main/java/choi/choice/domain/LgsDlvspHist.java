package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lgs_dlvsp_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LgsDlvspHist {

    @Id
    @Column(name = "hist_turn")
    private Integer histTurn;

    @EmbeddedId
    private LgsDlvspPK lgsDlvspPK;

    @Column(name = "clm_no")
    private String clmNo;

    @Column(name = "dlv_pcupsp_sect_cd")
    private String dvlPcupspSectCd;

    @Column(name = "addrse_nm")
    private String addrseNm;

    @Column(name = "dlv_sect_cd")
    private String dlvSectCd;

    @Column(name = "addrse_post_no")
    private String addrsePostNo;

    @Column(name = "addrse_base_addr")
    private String addrseBaseAddr;

    @Column(name = "addrse_detail_addr")
    private String addrseDetailAddr;

    @Column(name = "otskr_dlv_yn")
    private String otskrDlvYn;

    @Column(name = "addrse_mobil_no")
    private String addrseMobilNo;

    @Column(name = "addrse_tel_no")
    private String addrseTeleNo;

    @Column(name = "dlv_memo")
    private String dlvMemo;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;
}
