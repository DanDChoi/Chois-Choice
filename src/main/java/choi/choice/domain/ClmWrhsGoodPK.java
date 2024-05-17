package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClmWrhsGoodPK implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String clmNo;
    private int clmWrhsGoodTurn;

    public ClmWrhsGoodPK() {

    }

    public ClmWrhsGoodPK(String clmNo, int clmWrhsGoodTurn) {
        this.clmNo = clmNo;
        this.clmWrhsGoodTurn = clmWrhsGoodTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        ClmWrhsGoodPK clmWrhsGoodPK = (ClmWrhsGoodPK) obj;

        if (this.clmNo.equals(clmWrhsGoodPK.clmNo) && this.clmWrhsGoodTurn == clmWrhsGoodPK.clmWrhsGoodTurn) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clmNo, clmWrhsGoodTurn);
    }
}
