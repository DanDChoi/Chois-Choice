package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class MbrLoginLogPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mbrNo;

    /**
     * LOGIN / LOGOUT / LOGIN_FAILR
     */
    private String mbrLoginCd;

    private java.util.Date logOccurDt;

    public MbrLoginLogPK() {

    }

    public MbrLoginLogPK(String mbrNo, String mbrLoginCd, Date logOccurDt) {
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

        if (this.mbrNo.equals(mbrLoginLogPK.mbrNo) && this.mbrLoginCd.equals(mbrLoginLogPK.mbrLoginCd) || this.logOccurDt == mbrLoginLogPK.logOccurDt) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrNo, mbrLoginCd, logOccurDt);
    }
}
