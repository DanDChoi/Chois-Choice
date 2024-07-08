package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ClmGoodPK implements Serializable {

    private final static long serialVersionUID = 1L;

    private String clmNo;
    private Integer clmGoodTurn;

    public ClmGoodPK() {

    }

    public ClmGoodPK(String clmNo, Integer clmGoodTurn) {
        this.clmNo = clmNo;
        this.clmGoodTurn = clmGoodTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        ClmGoodPK clmGoodPK = (ClmGoodPK) obj;

        if (this.clmNo.equals(clmGoodPK.clmNo) && this.clmGoodTurn == clmGoodPK.clmGoodTurn) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clmNo, clmGoodTurn);
    }
}
