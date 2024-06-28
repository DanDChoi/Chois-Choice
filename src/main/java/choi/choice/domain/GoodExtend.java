package choi.choice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodExtend {

    private Good good;

    private List<GoodItm> goodItms;

    private List<GoodImg> goodImgList;

}
