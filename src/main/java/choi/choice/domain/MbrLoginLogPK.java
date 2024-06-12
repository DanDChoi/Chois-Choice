package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class MbrLoginLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mbrNo;

    /**
     * LOGIN / LOGOUT / LOGIN_FAILR
     */
    private String mbrLoginCd;

    private java.util.Date logOccurDt;

    public MbrLoginLogPK() {

    }

    public MbrLoginLogPK(Long mbrNo, String mbrLoginCd, Date logOccurDt) {
        this.mbrNo = mbrNo;
        this.mbrLoginCd = mbrLoginCd;
        this.logOccurDt = logOccurDt;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        MbrLoginLogPK mbrLoginLogPK = (MbrLoginLogPK) obj;

        if (this.mbrNo == mbrLoginLogPK.mbrNo && this.mbrLoginCd.equals(mbrLoginLogPK.mbrLoginCd) || this.logOccurDt == mbrLoginLogPK.logOccurDt) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrNo, mbrLoginCd, logOccurDt);
    }
}
