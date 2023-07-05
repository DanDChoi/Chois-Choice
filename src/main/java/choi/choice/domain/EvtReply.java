package choi.choice.domain;

import javax.persistence.Column;
import javax.persistence.Id;

public class EvtReply {

    @Column(name = "evt_no")
    private String evtNo;
    @Column(name = "reply_sn")
    private String replySn;
    @Column(name = "reply_cont")
    private String replyCont;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;}
