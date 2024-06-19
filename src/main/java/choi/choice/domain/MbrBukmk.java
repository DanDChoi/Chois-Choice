package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_bukmk")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrBukmk {
    @Id
    @Column(name = "bukmk_sn", unique = true)
    private String bukmkSn;

    /**
     * 북마크유형 GOD / EVT
     */
    @Column(name = "bukmk_tp_cd")
    private String bukmkTpCd;

    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "good_no")
    private String goodNo;

    @Column(name = "evt_no")
    private String evtNo;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

}
