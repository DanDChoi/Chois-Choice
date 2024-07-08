package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good_review_atch_file")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodReviewAtchFile {

    @Id
    @Column(name = "good_no")
    private String goodNo;

    @Id
    @Column(name = "good_evl_turn")
    private Integer goodEvlTurn;

    @Id
    @Column(name = "atch_file_turn")
    private Integer atchFileTurn;

    /**
     * IMG 이미지, MOVI 동영상
     */
    @Column(name = "atchmnfl_sect_cd")
    private String atchmnflSectCd;

    @Column(name = "atch_file_nm")
    private String atchFileNm;

    @Column(name = "atch_file_url")
    private String atchFileUrl;

    @Column(name = "movi_atch_file_url")
    private String moviAtchFileUrl;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

}
