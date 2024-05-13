package choi.choice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "lgs_dlv")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LgsDlv {

    @EmbeddedId
    private LgsDlvPK lgsDlvPK;

    @Column(name = "reg_dt")
    private java.util.Date regDt;

    @Column(name = "regtr_id")
    private String regtrId;

    @Column(name = "udt_dt")
    private java.util.Date udtDt;

    @Column(name = "udter_id")
    private String udterId;
}
