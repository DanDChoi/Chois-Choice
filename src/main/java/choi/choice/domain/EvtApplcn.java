package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evt_applcn")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvtApplcn {

    /**
     * 이벤트 참여 일련번호
     * 이벤트에 대한 참여 일련번호
     */
    @Id
    @Column(name = "evt_partcptn_sn")
    private int evtPartcptnSn;

    @Column(name = "evt_no")
    private String evtNo;

    @Column(name = "free_gift_turn")
    private int freeGiftTurn;

    /**
     * 응모회원 구분코드
     * 비회원 NMBR 회원 MBR 임직원 EMP
     */
    @Column(name = "applcn_mbr_sect_cd")
    private String applcnMbrSectCd;

    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "applcn_dt")
    private java.util.Date applcnDt;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
