package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ord")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ord {

    @Id
    @Column(name = "ord_no", unique = true)
    private String ordNo;
    @Column(name = "ord_dt")
    private String ordDt;
    @Column(name = "ord_status")
    private String ordStatus;
    @Column(name = "mbr_no")
    private Long mbrNo;
    @Column(name = "cstm_nm")
    private String cstmNm;
    @Column(name = "cstm_email")
    private String cstmEmail;
    @Column(name = "post_no")
    private String postNo;
    @Column(name = "base_addr")
    private String baseAddr;
    @Column(name = "detail_addr")
    private String detailAddr;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
