package choi.choice.framework.data;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class HostSocket implements Serializable {

    @NotEmpty(message = "호스트 IP가 필요합니다.")
    private String ip;
    @Range(min = 2, message = "호스트 PORT가 필요합니다.")
    private int port;
    private boolean isSSL = false;
    private String charset = "UTF-8";

    public HostSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public HostSocket(String ip, int port, boolean isSSL, String charset) {
        this.ip = ip;
        this.port = port;
        this.isSSL = isSSL;
        this.charset = charset;
    }
}
