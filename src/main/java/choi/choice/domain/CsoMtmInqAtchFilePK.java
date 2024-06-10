package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CsoMtmInqAtchFilePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer mtmSn;
    private Integer mtmAtchFileTurn;

    public CsoMtmInqAtchFilePK() {

    }

    public CsoMtmInqAtchFilePK(Integer mtmSn, Integer mtmAtchFileTurn) {
        this.mtmSn = mtmSn;
        this.mtmAtchFileTurn = mtmAtchFileTurn;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CsoMtmInqAtchFilePK csoMtmInqAtchFilePK = (CsoMtmInqAtchFilePK) obj;

        if (this.mtmSn == csoMtmInqAtchFilePK.getMtmSn() && this.mtmAtchFileTurn == csoMtmInqAtchFilePK.getMtmAtchFileTurn()) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mtmSn, mtmAtchFileTurn);
    }
}
