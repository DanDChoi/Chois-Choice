package choi.choice.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bsk {

    @Id
    @Column(name = "bsk_no", unique = true)
    private String bskNo;

    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;
}
