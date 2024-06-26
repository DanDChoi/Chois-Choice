package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bsk")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bsk {

    @Id
    @Column(name = "bsk_no", unique = true)
    private String bskNo;

    /**
     * 장바구니 유형코드
     * BSK : 장바구니 / DIRT : 바로구매
     */
    @Column(name = "bsk_tp_cd")
    private String bskTpCd;

    @Column(name = "mbr_no")
    private Long mbrNo;

    /**
     * 회원 구분코드
     * MBR : 회원 / NMBR : 비회원
     */
    @Column(name = "mbr_sect_cd")
    private String mbrSectCd;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
