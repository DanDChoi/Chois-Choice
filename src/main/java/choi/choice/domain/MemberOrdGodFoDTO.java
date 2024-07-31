package choi.choice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberOrdGodFoDTO {

    private static final long serialVersionUID = 146264237760548175L;

    private Mbr mbr;

    private Ord ord;

    private OrdGood ordGood;

    private GoodReview goodReview;

    private List<GoodReview> goodReviewList;

    private GoodReviewAtchFile goodReviewAtchFile;

    String[] goodReviewAtchFileNames;
    String[] deleteAtchFileTurn;

    private String dateStart;

    private String dateEnd;

    private String srchMonth;

    private List<String> deleteFileNm;

    private List<String> fileNames;

    private String filePath;

    private List<String> filePaths;

    private List<String> atchFileTurn;

    private String goodNo;

    private int fromNo;

    private int toNo;

    private String imgSizeCd = "100X132";

    private String pagingYn;

    private String type;

    private String mallId;

}
