package choi.choice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CpnHistPK {

    private static final long serialVersionUID = 1L;

    private String cpnNo;

    private int cpnHistNo;
    public CpnHistPK() {
    }

    public CpnHistPK(String cpnNo, int cpnHistNo) {
        this.cpnNo = cpnNo;
        this.cpnHistNo = cpnHistNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CpnHistPK cpnHistPK = (CpnHistPK) obj;

        if (this.cpnNo.equals(cpnHistPK.cpnNo) && this.cpnHistNo == cpnHistPK.cpnHistNo) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpnNo, cpnHistNo);
    }
}
