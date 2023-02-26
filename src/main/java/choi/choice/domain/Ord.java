package choi.choice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Ord {

    @Id
    @Column(name = "ord_no", unique = true)
    private String ordNo;
    @Column(name = "ord_dt")
    private String ordDt;
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
}
