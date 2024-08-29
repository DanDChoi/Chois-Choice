package choi.choice.framework.adapter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("AdapterHeader")
public class AdapterHeader {

    private String requestId;			// 요청ID
   	private String callerId;            // 요청자
    private String mallId;				// 몰ID
   	private String langCd;              // 언어코드
   	private String dvcCd;               // 장치코드
   	private String locale;				// 로케일
   	private String channel;				// 채널구분(웹/모바일)
   	private String userId;				// 보안관련 : user id.
   	private String password;			// 보안관련 : password.

   	private String authorization;		//카카오.
}
