package choi.choice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GoodItmHistPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodItmNo;
    private int goodItmHistNo;

    public GoodItmHistPK() {

    }

    public GoodItmHistPK(String goodItmNo, int goodItmHistNo) {
        this.goodItmNo = goodItmNo;
        this.goodItmHistNo = goodItmHistNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        GoodItmHistPK goodItmHistPK = (GoodItmHistPK) obj;

        if (this.goodItmNo.equals(goodItmHistPK.goodItmNo) && this.goodItmHistNo == goodItmHistPK.goodItmHistNo) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodItmNo, goodItmHistNo);
    }
}