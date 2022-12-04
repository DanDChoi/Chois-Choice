package choi.choice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class good {

    @Id
    private String good_no;
    private String good_nm;
    private String sale_beg_date;
    private String sale_end_date;
    private String color_nm;
    private String color_cd;

}
