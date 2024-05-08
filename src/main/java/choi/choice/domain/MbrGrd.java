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
public class MbrGrd {

    @Id
    @Column(name = "mbr_no", unique = true)
    private Long mbrNo; //회원번호
    @Column(name = "mbr_grd")
    private String mbrGrd;
    @Column(name = "grd_slctn_dt")
    private java.util.Date grdSlctnDt;
    @Column(name = "grd_beg_dt")
    private java.util.Date grdBegDt; //등급시작일
    @Column(name = "grd_end_dt")
    private java.util.Date grdEndDt; //등급종료일
    @Column(name = "acmslt_smon_beg_dt")
    private java.util.Date acmsltSmonBegDT; //집계시작일
    @Column(name = "acmslt_smon_end_dt")
    private java.util.Date acmsltSmonEndDT; //집계종료일
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
