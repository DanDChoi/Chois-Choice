package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class clm {

    @Id
    private String clm_no;
    private String mbr_no;
    private String ord_no;
    private String god_no;
}
