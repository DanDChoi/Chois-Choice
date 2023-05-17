package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clm")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clm {

    @Id
    @Column(name = "clm_no", unique = true)
    private String clmNo;
    @Column(name = "mbr_no")
    private Long mbrNo;
    @Column(name = "ord_no")
    private String ordNo;
    @Column(name = "god_no")
    private String godNo;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
