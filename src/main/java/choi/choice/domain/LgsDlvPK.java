package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class LgsDlvPK implements Serializable{

    private static final long serialVersionUID = 1L;

    private String ordNo;
    private Integer dlvPcupspTurn;
    private Integer dlvTurn;
    private Integer dlvPlcNo;

    public LgsDlvPK() {

    }

    public LgsDlvPK(String ordNo, Integer dlvPcupspTurn, Integer dlvTurn, Integer dlvPlcNo) {
        this.ordNo = ordNo;
        this.dlvPcupspTurn = dlvPcupspTurn;
        this.dlvTurn = dlvTurn;
        this.dlvPlcNo = dlvPlcNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        LgsDlvPK lgsDlvPK = (LgsDlvPK) obj;

        if (this.ordNo.equals(lgsDlvPK.ordNo)
        && this.dlvPcupspTurn == lgsDlvPK.dlvPcupspTurn
        && this.dlvTurn == lgsDlvPK.dlvTurn
        && this.dlvPlcNo == lgsDlvPK.dlvPlcNo) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordNo, dlvPcupspTurn, dlvTurn, dlvPlcNo);
    }
}
