package choi.choice.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class mbr {

    @NotNull
    private String mbr_id;
    private String mbr_nm;
    private String mbr_sex;
    private String mbr_email;
    private String mbr_pwd;
    private String mobil_no;
    private String mbr_post;
    private String mbr_base_addr;
    private String mbr_detail_addr;
    private String mbr_brthdy;
    private String join_date;
    private String mbr_stat_cd;

}