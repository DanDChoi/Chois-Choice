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


    @Column(name = "mbr_no")
    private Long mbrNo;
    @Column(name = "mbr_id")
    private String mbrId;
    @Column(name = "mbr_Nm")
    private String mbrNm;
    @Column(name = "mbr_sex")
    private String mbrSex;
    @Id
    @NotNull
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

    public Mbr(final String mbrEmail){
        this.mbrEmail = mbrEmail;
    }

//    @OneToOne
//    @JoinTable(name = "MBR_GRD",
//        joinColumns = @JoinColumn(name = "mbr_no")
//    )
//    private MbrGrd mbrGrd;

}
