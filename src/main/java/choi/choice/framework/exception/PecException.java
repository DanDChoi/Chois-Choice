package choi.choice.framework.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PecException extends RuntimeException{

    static final long serialVersionUID = 4984157272671390553L;

   	/* exception에 직접 출력할 메세지 */
   	protected String directMessage;

   	/* 메세지 파라미터 */
   	protected String[] params;

   	protected String customKey;

   	public PecException(String message) {
   		super(message);
   	}

   	public PecException(String[] messages) {
   		super(messages != null && messages.length > 0 ? messages[0] : "Undescribed Excepton");
   	}

   	public PecException(Throwable ex) {
   		super(ex);
   	}

   	public PecException(String message, Throwable ex) {
   		super(message, ex);
   	}

   	/* Exception을 초기화 한다. */
   	protected void init(String[] params) {
   		this.params = params;
   	}

   	/* Exception을 초기화 한다. */
   	protected void init(String key, String[] params) {
   		this.customKey = key;
   		this.params = params;
   	}
}
