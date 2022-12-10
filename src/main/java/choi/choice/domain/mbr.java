package choi.choice.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class mbr {

    @Id
    @Column(name = "mbr_no", unique = true)
    private Long mbrNo;
    @Column(name = "mbr_id")
    private String mbrId;
    @Column(name = "mbr_Nm")
    private String mbrNm;
    @Column(name = "mbr_sex")
    private String mbrSex;
    @NotNull
    @Column(name = "mbr_email")
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

}
