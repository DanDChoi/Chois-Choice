package choi.choice.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class SecureCode {

    private static final Pattern filenamePattern = Pattern.compile("^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9-_.]+[.]{1}+[a-z,A-Z,0-9]{3,}$+");

   	/**
   	 * 경로조작 및 자원삽입(path traversal) 파일명에 ./../ 등이 포함된 파일명은 해킹위험
   	 *
   	 * @param in
   	 */
   	public static void fileNameCheck(String in) {
   		if (!filenamePattern.matcher(in).matches()) {
   			throw new InvalidFileNameException(in, "File name that is not suitable.");
   		}
   	}

   	/**
   	 * 경로조작 및 자원삽입(path traversal) 파일명에 ./../ 등이 포함된 파일명은 해킹위험
   	 *
   	 * @param in
   	 */
   	public static void relativePathCheck(String in) {
   		if (in.indexOf("./") > -1 || in.indexOf("../") > -1 ) {
   			throw new InvalidFileNameException(in, "Contains a path separator that is not suitable.");
   		}
   	}

   	/**
   	 * 크로스사이트스크립팅(xss) 스크립트에 < > & " '은 해킹위험
   	 *
   	 * @param in
   	 * @return
   	 */
   	public static String xssReplaceAll(String in) {
   		StringBuffer stringBuffer = new StringBuffer();
   		if(null != in){
   			for (int j = 0; j < in.length(); j++) {
   				char c = in.charAt(j);
   				switch (c) {
   					case '<':
   						stringBuffer.append("&lt;");
   						break;
   					case '>':
   						stringBuffer.append("&gt;");
   						break;
   					case '&':
   						stringBuffer.append("&amp;");
   						break;
   					case '\"':
   						stringBuffer.append("&quot;");
   						break;
   					case '\'':
   						stringBuffer.append("&apos;");
   						break;
   					default:
   						stringBuffer.append(c);
   						break;
   				}

   			}
   		}
   		return stringBuffer.toString();

   	}

   	/**
   	 * json xss 방지
   	 *
   	 * @param in
   	 * @return
   	 * @throws JsonProcessingException
   	 * @throws IOException
   	 */
   	public static String xssReplaceAllForJson(String in) throws JsonProcessingException, IOException {
   		ObjectMapper mapper = new ObjectMapper();
   		JsonNode node = mapper.readTree(in);
   		node = clean(node);
   		return node.toString();
   	}

   	/**
   	 * json 소독
   	 *
   	 * @param node
   	 * @return
   	 */
   	public static JsonNode clean(JsonNode node) {

   		if (node.isValueNode()) { // Base case - we have a Number, Boolean or String
   			if (JsonNodeType.STRING == node.getNodeType()) {
   				// Escape all String values
   				return JsonNodeFactory.instance.textNode(Encode.forHtml(node.asText()));
   			} else {
   				return node;
   			}
   		} else { // Recursive case - iterate over JSON object entries
   			ObjectNode clean = JsonNodeFactory.instance.objectNode();
   			for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext();) {
   				Map.Entry<String, JsonNode> entry = it.next();
   				// Encode the key right away and encode the value recursively
   				clean.set(Encode.forHtml(entry.getKey()), clean(entry.getValue()));
   			}
   			return clean;
   		}
   	}

   	/**
   	 * http응답분할 http request 문자열에 \r\n을 사용시 해킹위험
   	 *
   	 * @param in
   	 * @return
   	 */
   	public static String httpSplit(String in) {
   		return in.replaceAll("\r\n", "");
   	}
}
