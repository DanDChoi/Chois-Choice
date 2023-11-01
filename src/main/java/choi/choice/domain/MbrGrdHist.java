package choi.choice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "mbr_grd_hist")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrGrdHist {

    @Id
    @Column(name = "mbr_no")
    private Long mbrNo; //회원번호
    @Column(name = "hist_dt")
    private java.util.Date histDt;
    @Column(name = "mbr_grd")
    private String mbrGrd;
    @Column(name = "grd_beg_dt")
    private String grdBegDt; //등급시작일
    @Column(name = "grd_end_dt")
    private String grdEndDt; //등급종료일
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
