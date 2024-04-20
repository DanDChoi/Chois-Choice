package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_cd")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SysCd {

    @Id
    @Column(name = "cd")
    private String cd; //ex MALL_MBR

    @Column(name = "cd_nm")
    private String cdNm; //ex 몰 회원

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
