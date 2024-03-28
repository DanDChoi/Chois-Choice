package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dsp_std_ctgry_cnnc")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StdDspCtgryCnnc {

    @Id
    @Column(name = "std_ctgry_no")
    private String stdCtgryNo;

    @Column(name = "dsp_ctgry_no")
    private String dspCtgryNo;

    @Column(name = "use_yn")
    private String useYn;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
