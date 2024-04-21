package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lgs_dlv")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LgsDlv {

    @Id
    @Column(name = "ord_no")
    private String ordNo;

    @Id
    @Column(name = "dlv_pcupsp_turn")
    private String dlvPcupspTurn;

    @Id
    @Column(name = "dlv_turn")
    private String dlvTurn;

    @Id
    @Column(name = "dmstc_dlv_cst_plc_sn")
    private String dmstcDlvCstPlcSn;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;
}
