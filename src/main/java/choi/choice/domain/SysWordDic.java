package choi.choice.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_word_dic")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SysWordDic {

    @Id
    @Column(name = "word_nm")
    private String wordNm;

    @Column(name = "eng_abrv_nm")
    private String engAbrvNm;

    @Column(name = "eng_dscr")
    private String engDscr;

    @Column(name = "kor_dscr")
    private String korDscr;

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
