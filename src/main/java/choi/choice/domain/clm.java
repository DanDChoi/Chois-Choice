package choi.choice.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class clm {

    @Id
    @Column(name = "clm_no", unique = true)
    private String clmNo;
    @Column(name = "mbr_no")
    private String mbrNo;
    @Column(name = "ord_no")
    private String ordNo;
    @Column(name = "god_no")
    private String godNo;
}
