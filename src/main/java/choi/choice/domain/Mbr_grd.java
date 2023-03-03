package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_grd")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mbr_grd {

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
