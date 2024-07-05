package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class GoodItmHistPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodItmNo;
    private Integer goodItmHistNo;

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
