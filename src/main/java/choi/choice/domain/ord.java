package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ord {

    @Id
    private String ord_no;
    private String ord_dt;
    private String mbr_no;
    private String cstm_nm;
    private String cstm_email;
    private String post_no;
    private String base_addr;
    private String detail_addr;
}
