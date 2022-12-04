package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class bsk {

    @Id
    private String bsk_no;
    private String mbr_no;
    private String session_id;
    private java.util.Date reg_dt;
}
