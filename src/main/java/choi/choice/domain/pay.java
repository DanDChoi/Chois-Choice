package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class pay {

    @Id
    private String pay_no;
    private String pay_mn_cd;
    private String pay_dt;
    private String ord_no;
    private String deal_tp_cd;
    private String dlv_cst_yn;
}
