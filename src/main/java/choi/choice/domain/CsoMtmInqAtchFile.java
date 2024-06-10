package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cso_mtm_inq_atch_file")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CsoMtmInqAtchFile {

    @EmbeddedId
    CsoMtmInqAtchFilePK csoMtmInqAtchFilePK;

    @Column(name = "mtm_inq_atch_file_nm")
    private String mtmInqAtchFileNm;

    @Column(name = "mtm_inq_atch_file_url")
    private String mtmInqAtchFileUrl;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
