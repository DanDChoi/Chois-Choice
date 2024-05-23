package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LgsDlvspPK implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String ordNo;
    private int dlvPcupspTurn;

    public LgsDlvspPK() {

    }

    public LgsDlvspPK(String ordNo, int dlvPcupspTurn) {
        this.ordNo = ordNo;
        this.dlvPcupspTurn = dlvPcupspTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        LgsDlvspPK lgsDlvspPK = (LgsDlvspPK) obj;

        if (this.ordNo.equals(lgsDlvspPK.ordNo) && this.dlvPcupspTurn == lgsDlvspPK.dlvPcupspTurn) {
            return true;
        }

        return false;

    }

    @Override
    public int hashCode() {
        return Objects.hash(ordNo, dlvPcupspTurn);
    }
}
