package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mbr_dlvsp")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//회원배송지
public class MbrDlvsp {

    @Id
    @Column(name = "mbr_no")
    private Long mbrNo;

    @Column(name = "dlv_adbuk_turn") //배송주소록순번
    private int dlvAdbukTurn;

    @Column(name = "adbuk_nm")
    private String adbukNm;

    @Column(name = "addrse_nm")
    private String addrseNm;

    @Column(name = "base_dlvsp_yn")
    private String baseDlvspYn;

    @Column(name = "dlv_addr_sect_cd") //배송주소구분코드 지번주소인지 도로명주소인지 구분
    private String dlvAddrSectCd;

    @Column(name = "post_no")
    private String postNo;

    @Column(name = "base_addr")
    private String baseAddr;

    @Column(name = "detail_addr")
    private String detailAddr;

    @Column(name = "tel_no")
    private String telNo;

    @Column(name = "mobil_no")
    private String mobilNo;

    @Column(name = "dlv_email")
    private String dlvEamil;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "udter_id")
    private String udterId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;}
