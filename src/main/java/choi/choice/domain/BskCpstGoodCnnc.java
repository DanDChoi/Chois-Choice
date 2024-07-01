package choi.choice.domain;

import javax.persistence.Column;
import javax.persistence.Id;

public class BskCpstGoodCnnc {

    @Id
    @Column(name = "bsk_no")
    private String bskNo;

    @Column(name = "good_turn")
    private int goodTurn;

    @Column(name = "cpst_good_turn")
    private int cpstGoodTurn;

    @Column(name = "pkage_good_tp_cd")
    private String pkageGoodTpCd;

    @Column(name = "cpst_good_qty")
    private int cpstGoodQty;

    @Column(name = "sort_seq")
    private int sortSeq;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
