package choi.choice.entities;

import choi.choice.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("mbrCrtfc")
public class MbrCrtfc extends AbstractEntity {

    private static final long serialVersionUID = 1L;

   	private Long mbrNo;

   	private String mbrCrtfcTpCd;

   	private String mbrCrtfcYn;

   	private java.util.Date mbrCrtfcDate;

   	private String empCrtfcMthdCd;

   	private String empCardNo;

   	private String ssoId;

   	private String rlnmCrtfcMobilNationNo;

   	private String rlnmCrtfcMobilNo;

   	private String rlnmCrtfcResultCd;

   	private String rlnmCrtfcDi;

   	private String rlnmCrtfcCi;

   	private String rlnmCrtfcVer;

   	private String b2eEmplCrtfcCd;

   	private String rlnmCrtfcIpin;

   	private String mbrEmail;

   	private String mbrEmailCrtfcToknId;

   	private java.util.Date mbrEmailCrtfcTmlmtDt;

   	private String regtrId;

   	private java.util.Date regDt;

   	private String udterId;

   	private java.util.Date udtDt;
}
