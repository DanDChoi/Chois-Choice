package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mbr_login_log")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrLoginLog {

    @EmbeddedId
    private MbrLoginLogPK mbrLoginLogPK;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "login_nation_cd")
    private String loginNationCd;

    @Column(name = "dvc_cd")
    private String dvcCd;

    @Column(name = "os_cd")
    private String osCd;

    @Column(name = "mobile_app_sect_cd")
    private String mobileAppSectCd;

}
