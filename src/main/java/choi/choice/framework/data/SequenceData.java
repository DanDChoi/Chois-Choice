package choi.choice.framework.data;

import lombok.Data;

import java.util.Map;

@Data
public class SequenceData {

    private String sequenceName;

   	/* 번호 코드를 위한 prefix */
   	private String prefix;

   	/* 순번 테이블 */
   	private String orderTable;

   	/* 순번 컬럼 */
   	private String orderColumn;

   	/* 순번 조건들 (where)*/
   	private Map<String, ?> condition;

   	/* lpad */
   	private Integer lpad;
}
