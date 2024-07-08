package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class LgsDlvspPK implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String ordNo;
    private Integer dlvPcupspTurn;

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
