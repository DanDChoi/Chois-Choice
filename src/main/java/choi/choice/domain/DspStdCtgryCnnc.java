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
public class DspStdCtgryCnnc {

    @Id
    @Column(name = "dsp_ctgry_no")
    private String dspCtgryNo;

    @Column(name = "std_ctgry_no")
    private String stdCtgryNo;

    @Column(name = "use_yn")
    private String useYn;
}
