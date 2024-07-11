package choi.choice.framework.data;

import lombok.Data;


/**
 * DB Where 절을 렌더링 하기 위한 오브젝트
 */
@Data
public class WhereCondition {

    /** where절의 조건 컬럼. */
   	String column;

   	/** where절의 조건 값. */
   	Object value;

}
