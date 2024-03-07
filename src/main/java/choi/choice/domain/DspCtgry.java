package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dsp_ctgry")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DspCtgry {

    @Id
    @Column(name = "dsp_ctgry_no")
    private String dspCtgryNo;

    @Column(name = "upper_dsp_ctgry_no")
    private String upperDspCtgryNo;

    @Column(name="dsp_ctgry_nm")
    private String dspCtgryNm;

    @Column(name="ctgry_dpth_no")
    private Integer ctgryDpthNo;

    @Column(name = "leaf_ctgry_yn")
    private String leafCtgryYn;

    @Column(name = "dsp_yn")
    private String dspYn;

    @Column(name = "delete_yn")
    private String deleteYn;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;

}
