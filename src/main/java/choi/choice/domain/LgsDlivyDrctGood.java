package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lgs_dlivy_drct_good")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LgsDlivyDrctGood {

    @Id
    @Column(name = "dlivy_drct_good_no") //출고지시상품번호
    private String dlivyDrctGoodNo;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "ord_good_turn")
    private String ordGoodTurn;

    @Column(name = "dlv_pcupsp_turn") //배송 수거지 순번
    private String dlvPcupspTurn;

    @Column(name = "dlv_turn") //배송지 순번
    private String dlvTurn;

    @Column(name = "dlivy_drct_tgt_yn") //출고지시 대상 여부
    private String dlivyDrctTgtYn;

    @Column(name = "dlivy_drct_yn") //출고지시 여부
    private String dlivyDrctYn;

    //출고지시 유형 코드
    //주문: ORD, 교환: EXCHG
    @Column(name = "dlivy_drct_tp_cd")
    private String dlivyDrctTpCd;

    @Column(name = "first_dlivy_drct_dt") //최초 출고지시 일시
    private String firstDlivyDrctDt;

    @Column(name = "dlivy_drct_dt") //출고지시 일시
    private String dlivyDrctDt;

    @Column(name = "dlivy_drct_qty") //출고지시 수량
    private String dlivyDrctQty;

    @Column(name = "dlivy_drct_cncl_qty") //출고지시 취소 수량
    private String dlivyDrctCnclQty;

    @Column(name = "dlivy_drct_cncl_dt") //출고지시 취소 일시
    private String dlivyDrctCnclDt;

    @Column(name = "dlivy_drct_cncl_requst_dt") //출고지시 취소 요청 일시
    private String dlivyDrctCnclRequstDt;

    @Column(name = "dlivy_prearnge_dt") //출고예정 일시
    private String dlivyPrearngeDt;

    @Column(name = "dlv_stat_cd") //배송 상태코드
    private String dlvStatCd;

    @Column(name = "dlivy_compt_qty") //출고완료 수량
    private String dlivyComptQty;

    @Column(name = "dlivy_compt_dt") //출고완료 일시
    private String dlivyComptDt;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;
}
