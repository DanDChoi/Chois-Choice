package choi.choice.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
public class mbr_grd {

    @Id
    @Column(name = "mbr_no", unique = true)
    private Long mbrNo; //회원번호
    @Column(name = "grd_beg_dt")
    private String grdBegDt; //등급시작일
    @Column(name = "grd_end_dt")
    private String grdEndDt; //등급종료일
    @Column(name = "reg_id")
    private String regId; //등록자
}
