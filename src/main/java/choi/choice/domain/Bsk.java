package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bsk")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bsk {

    @Id
    @Column(name = "bsk_no", unique = true)
    private String bskNo;

    @Column(name = "mbr_no")
    private Long mbrNo;
    @Column(name = "good_no")
    private String goodNo;
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
