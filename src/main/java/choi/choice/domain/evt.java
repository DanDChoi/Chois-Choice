package choi.choice.domain;

import lombok.Data;

@Data
public class evt {

    private String evt_no;
    private String eve_nm;
    private String evt_dscr;
    private java.util.Date evt_beg_dt;
    private java.util.Date evt_end_dt;
    private String reply_use_yn;
    private String reply_config_sn;
}
