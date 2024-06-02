package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class GoodHistPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodNo;
    private int goodHistNo;

    public GoodHistPK() {

    }

    public GoodHistPK(String goodNo, int goodHistNo) {
        this.goodNo = goodNo;
        this.goodHistNo = goodHistNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        GoodHistPK goodHistPK = (GoodHistPK) obj;

        if (this.goodNo.equals(goodHistPK.goodNo) && this.goodHistNo == goodHistPK.goodHistNo) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodNo, goodHistNo);
    }
}
