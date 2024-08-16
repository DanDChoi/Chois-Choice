package choi.choice.framework.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CdnUssrSerializer {
    private static final long serialVersionUID = -1L;
    private ObjectMapper mapper;
    private boolean useCdnUrl;

    public CdnUssrSerializer(boolean useCdnUrl) {
        this.useCdnUrl = useCdnUrl;
        this.mapper = new ObjectMapper();
    }
}
