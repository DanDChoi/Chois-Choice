package choi.choice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good_review")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoodReview {

    @Id
    @Column(name = "review_sn", unique = true)
    private String reviewSn;
    @Column(name = "good_no")
    private String goodNo;
    @Column(name = "good_review_turn")
    private String goodReviewTurn;
    @Column(name = "review_cont")
    private String reviewCont;
    @Column(name = "regtr_id")
    private String regtrId;
    @Column(name = "reg_dt")
    private java.util.Date regDt;
    @Column(name = "udter_id")
    private String udterId;
    @Column(name = "udt_dt")
    private java.util.Date udtDt;
}
