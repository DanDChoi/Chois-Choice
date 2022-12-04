package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class mbr_grd {

    private String mbr_no; //회원번호
    private String grd_beg_dt; //등급시작일
    private String grd_end_dt; //등급종료일
    private String reg_id; //등록자
}
