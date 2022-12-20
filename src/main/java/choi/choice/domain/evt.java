package choi.choice.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class evt {

    @Id
    @Column(name = "evt_no", unique = true)
    private String evtNo;
    private String eveNm;
    private String evtDscr;
    private java.util.Date evtBegDt;
    private java.util.Date evtEndDt;
    private String replyUseYn;
    private String replyConfigSn;
}
