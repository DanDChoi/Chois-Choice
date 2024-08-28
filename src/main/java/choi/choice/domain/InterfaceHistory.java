package choi.choice.domain;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interface_history")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterfaceHistory {

    @Id
    @Column(name = "request_id")
    private String requestId;

    @Column(name = "call_class_id")
    private String callClassId;

    @Column(name = "input_cont")
    private String inputCont;

    @Column(name = "output_cont")
    private String outputCont;

    @Column(name = "execut_beg_dt")
    private java.util.Date executBegDt;

    @Column(name = "exec_end_dt")
    private java.util.Date executEndDt;

    @Column(name = "msg_cont")
    private String msgCont;


}
