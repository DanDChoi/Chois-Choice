package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bsk_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BskGood {

    @Id
    @Column(name = "bsk_seq")
    private Long bskSeq;

    @Column(name = "bsk_no")
    private String bskNo;

    @Column(name = "good_turn")
    private int goodTurn;

    @Column(name = "good_no")
    private String goodNo;

    @Column(name = "itm_no")
    private String itmNo;

    @Column(name = "itm_qty")
    private int itmQty;

    @Column(name = "bsk_reg_dt")
    private java.util.Date bskRegDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
