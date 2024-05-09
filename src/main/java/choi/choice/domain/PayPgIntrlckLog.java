package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pay_pg_intrlck_log")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayPgIntrlckLog {

    // TODO PK 없는 JPA 엔티티 만들기

    @Column(name = "log_dt")
    private java.util.Date logDt;

    @Column(name = "log_tp_cd")
    private String logTpCd;

    @Column(name = "log_cont")
    private String logCont;

    @Column(name = "pay_no")
    private String payNo;

    @Column(name = "ord_no")
    private String ordNo;

    @Column(name = "clm_no")
    private String clmNo;

    @Column(name = "repair_no")
    private String repairNo;

    @Column(name = "mbrNo")
    private String mbrNo;

    @Column(name = "pg_aprv_no")
    private String pgAprvNo;

}
