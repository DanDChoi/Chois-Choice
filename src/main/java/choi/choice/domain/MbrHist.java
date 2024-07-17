package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mbr_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrHist {

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
    @Column(name = "mbr_stat_cd")
    private String mbrStatCd;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
    @Column(name = "hist_dt")
    private java.util.Date histDt;
}
