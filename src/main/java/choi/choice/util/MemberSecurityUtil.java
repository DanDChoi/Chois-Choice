package choi.choice.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MemberSecurityUtil {
    @Value("${interface.hpoint.crypto.key}")
   	private String key;

   	@Value("${interface.hpoint.salt}")
   	private String salt;

    /**
     * 평문을 SHA512으로 변환 한다
     * @param value : 일반 데이터
     * @return 암호화 된 데이터
     * @throws Exception
	 * @auth 정문식
     */
	public String generateSHA512(String value) {
		try {
			StringBuffer encryptData = new StringBuffer();

			MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
			String sum = (new StringBuilder()).append(value).append(key).toString();
			sha512.update(sum.getBytes());
			byte[] digest = sha512.digest();
			for (int i = 0; i < digest.length; i++) {
				String s = Integer.toHexString(digest[i] & 0xFF);
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				encryptData.append(s);
			}
			return encryptData.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

    /**
     * 암호화된 통합고객번호를 복호화한다.
     * @param plain : 암호화된 데이터
     * @return 복호화 된 데이터
     * @throws Exception
	 * @auth 정문식
     */
	public String decodeHdgmBase64(String plain) {
		if(plain == null || "".equals(plain) || "" == plain){
			return "";
		}
		String decodeStr = decodeString(plain);
		decodeStr = decodeStr.substring(3, decodeStr.length());
		decodeStr = decodeString(decodeStr);
		if (!decodeStr.startsWith("HDGM")) {
			return "";
		} else {
			decodeStr = decodeStr.substring(4, decodeStr.length());
			decodeStr = decodeString(decodeStr);
			decodeStr = decodeStr.substring(3, decodeStr.length());
			decodeStr = decodeString(decodeStr);
			if (!decodeStr.startsWith("20")) {
				return "";
			} else {
				decodeStr = decodeStr.substring(8, decodeStr.length());
				decodeStr = decodeString(decodeStr);
				if (!decodeStr.endsWith("SSO")) {
					return "";
				} else {
					decodeStr = decodeStr.substring(0, decodeStr.length() - 3);
					decodeStr = decodeString(decodeStr);
					return decodeStr;
				}
			}
		}
	}

	public static String decodeString(String base64)
	{
		return new String(decode(base64));
	}

	public static int getValue(char c)
	{
		if(c >= 'A' && c <= 'Z')
			return c - 65;
		if(c >= 'a' && c <= 'z')
			return (c - 97) + 26;
		if(c >= '0' && c <= '9')
			return (c - 48) + 52;
		if(c == '+')
			return 62;
		if(c == '/')
			return 63;
		return c != '=' ? -1 : 0;
	}

	public static byte[] decode(String base64)
	{
		int pad = 0;
		for(int i = base64.length() - 1; base64.charAt(i) == '='; i--)
			pad++;

		byte raw[] = new byte[(base64.length() * 6) / 8 - pad];
		int rawIndex = 0;
		int base64Len = base64.length();
		for(int i = 0; i < base64Len; i += 4)
		{
			int block = (getValue(base64.charAt(i)) << 18) + (getValue(base64.charAt(i + 1)) << 12) + (getValue(base64.charAt(i + 2)) << 6) + getValue(base64.charAt(i + 3));
			for(int j = 0; j < 3 && rawIndex + j < raw.length; j++)
				raw[rawIndex + j] = (byte)(block >> 8 * (2 - j) & 0xff);

			rawIndex += 3;
		}

		return raw;
	}

	public String decrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(Base64.decodeBase64(text));
		return new String(results, "UTF-8");
	}

	public String encrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length)
			len = keyBytes.length;
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		return Base64.encodeBase64String(results).trim();
	}

	public static boolean isContainedKorean(String target) {
		if (target == null) return false;
		return target.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*");
	}

	/**
	 * 지정한 문자열을 SHA256 암호화 한다.
	 *
	 * @param message 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws Exception 암호화 할때 발생하는 에러.
	 */
	public String generateSHA256(String message) {

		String SHA = null;

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(message.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			SHA = "Exception : " + e.getMessage();
		}

		return SHA;

	}
}
