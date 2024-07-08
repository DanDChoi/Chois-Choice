package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class OrdGoodPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ordNo;
    private Integer ordGoodTurn;

    public OrdGoodPK(){

    }

    public OrdGoodPK(String ordNo, int ordGoodTurn) {
        this.ordNo = ordNo;
        this.ordGoodTurn = ordGoodTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        OrdGoodPK ordGoodPK = (OrdGoodPK) obj;

        if (this.ordNo.equals(ordGoodPK.ordNo) && this.ordGoodTurn == ordGoodPK.ordGoodTurn) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordNo, ordGoodTurn);
    }
}
