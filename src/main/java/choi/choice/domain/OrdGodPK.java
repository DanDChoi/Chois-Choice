package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrdGodPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ordNo;
    private String ordGodTurn;

    public OrdGodPK(){

    }

    public OrdGodPK(String ordNo, String ordGodTurn) {
        this.ordNo = ordNo;
        this.ordGodTurn = ordGodTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        OrdGodPK ordGodPK = (OrdGodPK) obj;

        if (this.ordNo.equals(ordGodPK.ordNo) && this.ordGodTurn.equals(ordGodPK.ordGodTurn)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordNo, ordGodTurn);
    }
}
