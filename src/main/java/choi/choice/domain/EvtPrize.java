package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@Table(name = "evt_prize")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvtPrize {

    @Id
    @Column(name = "evt_partcptn_sn")
    private Integer evtPartcptnSn;

    @Column(name = "prize_turn")
    private Integer prizeTurn;

    @Column(name = "prize_rank")
    private Integer prizeRank;

    @Column(name = "evt_no")
    private String evtNo;

    @Column(name = "prize_dt")
    private java.util.Date prizeDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
