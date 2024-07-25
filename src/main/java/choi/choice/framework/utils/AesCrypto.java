package choi.choice.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

@Slf4j
@Component
public class AesCrypto {

    /**
   	 * 대상을 암호화 한다.
   	 *
   	 * @author		Kent TW Kim
   	 * @desc		대상을 암호화 한다.
   	 * @param		message	암호화 대상
   	 * @return		String	암호화 결과
   	 * @throws Exception
   	 */
   	public String encrypt(String message) throws Exception {
   		String CRYPTO_SEED_PASSWORD = "sfm@!crypto";

   		byte[] rawKey = getRawKey(CRYPTO_SEED_PASSWORD.getBytes("UTF-8"));
   		byte[] result = encrypt(rawKey, message.getBytes("UTF-8"));

   		return toHex(result);
   	}

   	/**
   	 * 대상을 복호화 한다.
   	 *
   	 * @author		Kent TW Kim
   	 * @desc		대상을 복호화 한다.
   	 * @param		encryptedMessage	복호화 대상
   	 * @return		String	복호화 결과
   	 * @throws Exception
   	 */
   	public String decrypt(String encryptedMessage) throws Exception	{
           String CRYPTO_SEED_PASSWORD = "sfm@!crypto";

   		byte [] rawKey = getRawKey(CRYPTO_SEED_PASSWORD.getBytes("UTF-8"));
   		byte [] enc = toByte(encryptedMessage);
   		byte [] result = decrypt(rawKey, enc);

   		return new String(result);
   	}

   	private byte[] getRawKey(byte[] seed) throws Exception {
   		KeyGenerator kgen = KeyGenerator.getInstance("AES");
   		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
   		sr.setSeed(seed);
   	    kgen.init(128, sr); // 192 and 256 bits may not be available
   	    SecretKey skey = kgen.generateKey();
   	    byte[] raw = skey.getEncoded();

   	    return raw;
   	}


   	private byte[] encrypt(byte[] raw, byte[] srcMsg) throws Exception {
   	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
   		Cipher cipher = Cipher.getInstance("AES");
   	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
   	    byte[] encrypted = cipher.doFinal(srcMsg);

   		return encrypted;
   	}

   	private byte[] decrypt(byte[] raw, byte[] encryptedMsg) throws Exception {
   	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
   		Cipher cipher = Cipher.getInstance("AES");
   	    cipher.init(Cipher.DECRYPT_MODE, skeySpec);
   	    byte[] decrypted = cipher.doFinal(encryptedMsg);

   	    return decrypted;
   	}

   	public String toHex(String txt) {
   		return toHex(txt.getBytes());
   	}

   	public String fromHex(String hex) {
   		return new String(toByte(hex));
   	}

   	public byte[] toByte(String hexString) {
   		int len = hexString.length()/2;
   		byte[] result = new byte[len];

   		for(int i = 0; i < len; i++) {
   			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
   		}

   		return result;
   	}

   	public String toHex(byte[] buf) {
   		if(buf == null) {
   			return "";
   		}
   		StringBuffer result = new StringBuffer(2*buf.length);
   		for(int i = 0; i < buf.length; i++) {
   			appendHex(result, buf[i]);
   		}

   		return result.toString();
   	}

   	private void appendHex(StringBuffer sb, byte b) {
           String HEX = "0123456789ABCDEF";
   		sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
   	}
}
