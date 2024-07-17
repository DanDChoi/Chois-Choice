package choi.choice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mbr")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mbr {

    private String mallId;
    private String langCd;
    private String dvcCd;

    @Column(name = "mbr_no")
    private Long mbrNo;
    @Id
    @NotNull
    @Column(name = "mbr_id")
    private String mbrId;
    @Column(name = "mbr_Nm")
    private String mbrNm;
    /**
     * ACT / DRMNCY / SECSN
     */
    @Column(name = "mbr_stat_cd")
    private String mbrStatCd;
    /**
     * NMBR / MALL_MBR
     */
    @Column(name = "mbr_tp_cd")
    private String mbrTpCd;
    @Column(name = "mbr_login_last_failr_count")
    private Integer mbrLoginLastFailrCount;
    @Column(name = "mbr_sex")
    private String mbrSex;
    @Column(name = "mbr_email", unique = true)
    private String mbrEmail;
    @Column(name = "mbr_pwd")
    private String mbrPwd;
    @Column(name = "mobile_no")
    private String mobilNo;
    @Column(name = "mbr_post")
    private String mbrPost;
    @Column(name = "mbr_base_addr")
    private String mbrBaseAddr;
    @Column(name = "mbr_detail_addr")
    private String mbrDetailAddr;
    @Column(name = "mbr_brthdy")
    private String mbrBrthdy;
    @Column(name = "join_date")
    private String joinDate;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    public Mbr(final String mbrEmail){
        this.mbrEmail = mbrEmail;
    }

//    @OneToOne
//    @JoinTable(name = "MBR_GRD",
//        joinColumns = @JoinColumn(name = "mbr_no")
//    )
//    private MbrGrd mbrGrd;

}
