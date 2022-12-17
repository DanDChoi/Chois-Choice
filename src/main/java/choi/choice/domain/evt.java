package choi.choice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class evt {

    @Id
    private String evtNo;
    private String eveNm;
    private String evtDscr;
    private java.util.Date evtBegDt;
    private java.util.Date evtEndDt;
    private String replyUseYn;
    private String replyConfigSn;
}
