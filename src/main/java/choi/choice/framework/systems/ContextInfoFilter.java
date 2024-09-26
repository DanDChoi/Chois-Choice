package choi.choice.framework.systems;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 요청 실행에 대한 문맥 정보를 SLF4j의 MDC에 추가한다.
 */
@Component
@Slf4j
public class ContextInfoFilter implements Filter {
	@Autowired
	ExecutionContext executionContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		inject(request, response);
		long start = System.currentTimeMillis();
		try {
			chain.doFilter(request, response);
		} finally {
			String queryString = req.getQueryString();
			if (StringUtils.isNotEmpty(queryString)) {
				MDC.put("requestParams", req.getQueryString());
			}

			long end = System.currentTimeMillis();
			long elapsed = end - start;
			MDC.put("executionElapsed", String.valueOf(elapsed));

			MDC.remove("requestParams");
			MDC.remove("executionElapsed");
			cleanup();
		}
	}

	public String convertWithIteration(Map<String, String[]> map) {
		StringBuilder mapAsString = new StringBuilder();
		for (String key : map.keySet()) {
			String[] value = map.get(key);
			mapAsString.append(key + "=" + values(value) + "&");
		}
		mapAsString.delete(mapAsString.length() - 1, mapAsString.length());
		return mapAsString.toString();
	}

	private String values(String[] values) {
		if (values == null) {
			return "";
		}
		if (values.length == 1) {
			return values[0];
		}
		StringBuilder valueString = new StringBuilder();
		for (String value : values) {
			valueString.append(value).append(",");
		}
		valueString.delete(valueString.length() - 1, valueString.length());
		return valueString.toString();
	}

	private void inject(ServletRequest request, ServletResponse response) {
		try {
			executionContext.inject((HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception e) {
			// Context Info 를 획득하는 과정이 어플리케이션의 실행을 막아서는 않됨
			log.error("Context Info Error", e);
		}
	}

	private void cleanup() {
		executionContext.cleanCurrentThread();
	}

	@Override
	public void destroy() {
	}
}
