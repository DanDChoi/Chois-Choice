package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MbrIssuCpnHistPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mbrCpnNo;
    private Integer histTurn;

    public MbrIssuCpnHistPK() {

    }

    public MbrIssuCpnHistPK(String mbrCpnNo, Integer histTurn) {
        this.mbrCpnNo = mbrCpnNo;
        this.histTurn = histTurn;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        MbrIssuCpnHistPK mbrIssuCpnHistPK = (MbrIssuCpnHistPK) obj;

        if (this.mbrCpnNo.equals(mbrIssuCpnHistPK.mbrCpnNo) && this.histTurn == mbrIssuCpnHistPK.histTurn) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbrCpnNo, histTurn);
    }
}
