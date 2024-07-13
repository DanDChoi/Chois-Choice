package choi.choice.service;

import choi.choice.domain.SystemPK;
import choi.choice.framework.commons.LocaleService;
import choi.choice.framework.data.SequenceData;
import choi.choice.framework.data.WhereCondition;
import choi.choice.framework.data.mobile.MobileDevice;
import choi.choice.framework.enums.DatabaseType;
import choi.choice.framework.exception.NotFindConfigException;
import choi.choice.framework.exception.NotSupportedException;
import choi.choice.framework.utils.JsonUtil;
import choi.choice.framework.utils.SecureCode;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class IdGenService {
    /**
   	 * UUID에서 생략할 문자.
   	 */
   	static final char UUID_REMOVE_CHAR = '-';

   	/* web.xml에서 선언된 mall 아이디 환경변수 명 */
   	final static String MALL_ID_SYSTEM_VARIABLE_ID = "mall.id";

   	String oracleSequenceMybatisId;

   	String oracleNumberMybatisId;

   	String oracleOrderMybatisId;

   	/**
   	 * mobile device 구분자.
   	 */
   	@Value("${base.device.mobile}")
   	private String mobileDevice;

   	/**
   	 * mobile app 구분자.
   	 */
   	@Value("${base.device.mobile_app}")
   	private String mobileApp;

   	/**
   	 * 기본 장치
   	 */
   	@Value("${base.device}")
   	private String device;

    /**
   	 * UUID Version4 (Random Number)를 생성 후 '-'이 제거된 uuid를 리턴 한다. <br/>
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * getIdGenService().generateUUID() = bf3151cbaa7448acb89e1076fe2c057c (32
   	 * 바이트)
   	 *
   	 * @return String uuid
   	 * @since 2015
   	 */
   	public String generateUUID()  {
   		RandomBasedGenerator generator = Generators.randomBasedGenerator();
   		return StringUtils.remove(generator.generate().toString(), UUID_REMOVE_CHAR);
   	}

   	/**
   	 * 컬럼명 뒷 부분이 "일련번호(SN)" 일때 시퀀스 정보를 얻어 온다
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * getIdGenService().generateSequence(sqlSession1, "AA_SEQ",
   	 * DatabaseType.ORACLE)) = 5
   	 *
   	 * @param session
   	 *            SqlSession
   	 * @param sequenceName
   	 *            시퀀스 명
   	 * @param dbType
   	 *            데이터베이스 타입
   	 * @return Big integer 시퀀스 값
   	 * @since 2015
   	 */
   	public Long generateDBSequence(SqlSession session, String sequenceName, DatabaseType dbType) {

   		// DB 타입 지원 여부를 확인 한다.
   		checkSupported(dbType);

   		BigInteger sequence = BigInteger.valueOf(0L);

   		// pro.properties에 오라클 전용 시퀀스값이 없을 경우
   		if (StringUtils.isEmpty(oracleSequenceMybatisId)) {
   			throw new NotFindConfigException(null);
   		}

   		SequenceData data = new SequenceData();
   		data.setSequenceName(sequenceName);
   		sequence = session.selectOne(oracleSequenceMybatisId, data);

   		return sequence.longValue();
   	}

   	/**
   	 * 컬럼명 뒷 부분이 "번호(NO)" 일때 .
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * idGenService.generateDBNumber(sqlSession1, "AA_SEQ", "IT",
   	 * DatabaseType.ORACLE)
   	 *
   	 * @param session      SqlSession
   	 * @param sequenceName 시퀀스 명
   	 * @param prefix       코드 Prefix
   	 * @param dbType       데이터베이스 타입
   	 * @return String Prefix + 날짜(YYYYMMDD) + 시퀀스번호
   	 * @since 2015
   	 */
   	public String generateDBNumber(SqlSession session, String sequenceName, String prefix, DatabaseType dbType) {
   		return this.generateDBNumber(session, sequenceName, prefix, 7, dbType);
   	}

   	/**
   	 * 컬럼명 뒷 부분이 "번호(NO)" 일때 .(LPAD 자릿수 지정)
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * idGenService.generateDBNumber(sqlSession1, "AA_SEQ", "IT",
   	 * DatabaseType.ORACLE)
   	 *
   	 * @param session      SqlSession
   	 * @param sequenceName 시퀀스 명
   	 * @param prefix       코드 Prefix
   	 * @param lpad         LPAD 자릿수
   	 * @param dbType       데이터베이스 타입
   	 * @return String Prefix + 날짜(YYYYMMDD) + 시퀀스번호
   	 * @since 2015
   	 */
   	public String generateDBNumber(SqlSession session, String sequenceName, String prefix, Integer lpad, DatabaseType dbType) {
   		// DB 타입 지원 여부를 확인 한다.
   		checkSupported(dbType);

   		SequenceData data = new SequenceData();
   		data.setSequenceName(sequenceName);
   		data.setPrefix(prefix);
   		data.setLpad(lpad);

   		// pro.properties에 오라클 전용 시퀀스값이 없을 경우
   		if (StringUtils.isEmpty(oracleNumberMybatisId)) {
   			throw new NotFindConfigException(null);
   		}

   		return session.selectOne(oracleNumberMybatisId, data);
   	}

   	/**
   	 * 컬럼명 뒷 부분이 "순번(TURN)" 일때 .
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * Map<String, Integer> conditions = Maps.newHashMap();<br/>
   	 * conditions.put("pk1", 1);<br/>
   	 * conditions.put("pk2", 1);<br/>
   	 * <p/>
   	 * idGenService.generateDBOrder(sqlSession1, "aa_seq_table", "pk3",
   	 * conditions, DatabaseType.ORACLE)<br/>
   	 *
   	 * @param session
   	 *            SqlSession
   	 * @param tableName
   	 *            테이블 명
   	 * @param orderColumn
   	 *            최대값 대상 컬럼
   	 * @param conditions
   	 *            WHERE 조건절에 컬럼, 값 형태의 맵
   	 * @param dbType
   	 *            데이터베이스 타입
   	 * @return Big integer 순번
   	 * @since 2015
   	 */
   	public Integer generateDBOrder(SqlSession session, String tableName, String orderColumn,
                                      Map<String, Object> conditions, DatabaseType dbType) {

   		// DB 타입 지원 여부를 확인 한다.
   		checkSupported(dbType);

   		Map<String, Object> map = Maps.newHashMap();
   		map.put("orderTable", tableName);
   		map.put("orderColumn", orderColumn);

   		List<WhereCondition> list = Lists.newArrayList();

   		if (conditions != null) {
   			for (String key : conditions.keySet()) {

   				WhereCondition condition = new WhereCondition();
   				condition.setColumn(key);

   				if (conditions.get(key) instanceof String) {
   					String value = String.valueOf(conditions.get(key));
   					/**
   					 * fortify - HTTP응답분할
   					 */
   					value = SecureCode.httpSplit(value);
   					condition.setValue(value);
   				}
   				else {
   					condition.setValue(conditions.get(key));
   				}
   				list.add(condition);
   			}

   		}
   		map.put("orderCondition", list);

   		// pro.properties에 오라클 전용 시퀀스값이 없을 경우
   		if (StringUtils.isEmpty(oracleOrderMybatisId)) {
   			throw new NotFindConfigException(null);
   		}

   		return session.selectOne(oracleOrderMybatisId, map);
   	}

   	/**
   	 * [메서드 설명].
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * [사용 방법 설명].
   	 *
   	 * @param mall
   	 *            [설명]
   	 * @param device
   	 *            [설명]
   	 * @param lang
   	 *            [설명]
   	 * @return System pk [설명]
   	 * @since 2015
   	 */
   	public SystemPK generateSystemPK(String mall, String device, String lang) {

   		SystemPK pk = new SystemPK();
   		pk.setMall(mall);
   		pk.setDevice(device);
   		pk.setLang(lang);

   		log.debug("current System PK \n{}", JsonUtil.marshallingJsonWithPretty(pk));

   		return pk;
   	}

   	/**
   	 * 서비스를 위한 시스템 PK 오브젝트를 생성 한다.
   	 * <p/>
   	 * <p/>
   	 * <p/>
   	 * idGenService.generateSystemPK(Mall.HFM, Channel.WEB, request);
   	 *
   	 * @param mall
   	 *            몰 아이디 enum
   	 * @param channel
   	 *            진입경로 enum
   	 * @param request
   	 *            [설명]
   	 * @return System pk PK 오브젝트
   	 * @since 2015
   	 */
   	public SystemPK generateSystemPK(String mall, String device, HttpServletRequest request) {

   		SystemPK pk = new SystemPK();
   		pk.setMall(mall);
   		pk.setDevice(device);
   		pk.setLang(LocaleService.getCurrentLang(request));

   		MobileDevice mobileDevice = getMobileDevice(request);
   		pk.setDeviceType(mobileDevice.getDeviceType().name());
   		pk.setDevicePlatform(mobileDevice.getDevicePlatform().name());
   		pk.setApp(getApp(request));
   		log.debug("current System PK \n{}", JsonUtil.marshallingJsonWithPretty(pk));

   		return pk;
   	}

   	/**
   	 * web.xml에서 env-entry 환경변수 값을 조회 한다.
   	 * <p/>
   	 * <p/>
   	 *
   	 * @param key
   	 *            [설명]
   	 * @return String [설명]
   	 * @since 2015
   	 */
   	public String getEnvValue(String key) {
   		try {
   			InitialContext initCtx = new InitialContext();
   			Context defCtx = (Context) initCtx.lookup("java:comp/env");
   			String value = (String) defCtx.lookup(key);

   			return value;
   		} catch (NamingException e) {
   			throw new RuntimeException(e);
   		}

   	}

   	/* 평문을 SHA256으로 변환 한다. */
   	public static String generateSHA256(String value) {

   		try {
   			StringBuffer encryptData = new StringBuffer();

   			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
   			sha256.update(value.getBytes());
   			byte[] digest = sha256.digest();
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

   	/* 평문을 salt를 사용하여 변환 한다. */
   	public String generateSHA256(String str, String salt) {
   		try {
   			MessageDigest md = MessageDigest.getInstance("SHA-256");
   			md.reset();
   			md.update(salt.getBytes());
   			byte[] raw = md.digest(str.getBytes());

   			md.reset();
   			raw = md.digest(raw);

   			byte[] encodedBytes = Base64.encodeBase64(encodeHex(raw).getBytes());
   			String passACL = new String(encodedBytes);

   			return passACL;
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}
   	}

   	private String encodeHex(byte[] b) {
   		char[] c = Hex.encodeHex(b);
   		return new String(c);
   	}

   	/* 평문을 SHA512으로 변환 한다. */
   	public static String generateSHA512(String value) {

   		try {
   			StringBuffer encryptData = new StringBuffer();

   			MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
   			sha512.update(value.getBytes());
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

   	/* 평문을 MD5으로 변환 한다. */
   	public static String generateMD5(String value) {
   		// Md5PasswordEncoder encoder = new Md5PasswordEncoder();
   		// return encoder.encodePassword(value, "");
   	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
   		return encoder.encode(value);

   	}

   	/* 임시 비밀 번호 발급 사용방법 : getIdGenService().generateTempPassword() */
   	public static String generateTempPassword() {

   		try {
   			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
   			String randomNum = new Integer(prng.nextInt()).toString();
   			MessageDigest sha = MessageDigest.getInstance("SHA-1");
   			byte[] result = sha.digest(randomNum.getBytes());
   			String value = hexEncode(result);

   			return StringUtils.substring(value, 7, 14);
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}
   	}

   	/*
   	  PO 개편 임시비밀번호
   	 */
   	public static String generateTempPassword2() {
   		try {
   			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
   			String randomNum = new Integer(prng.nextInt()).toString();
   			MessageDigest sha = MessageDigest.getInstance("SHA-1");
   			byte[] aInput = sha.digest(randomNum.getBytes());

   			StringBuilder result = new StringBuilder();
   			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
   			for (int idx = 0; idx < aInput.length; ++idx) {
   				byte b = aInput[idx];
   				result.append(digits[(b & 0xf0) >> 4]);
   				result.append(digits[b & 0x0f]);
   			}
   			return StringUtils.substring(result.toString(), 4, 14);
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}
   	}

   	/* 임직원 인증 코드 발급 16자리*/
   	public static String generateEmpCrtfcCode() {
   		try {
   			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
   			String randomNum = new Integer(prng.nextInt()).toString();
   			MessageDigest sha = MessageDigest.getInstance("SHA-1");
   			byte[] result = sha.digest(randomNum.getBytes());
   			String value = hexEncode(result);

   			return StringUtils.substring(value, 0, 16);
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}
   	}

   	/* 쿠폰 아이디 발급, count 수에 맞게 쿠폰을 발급 한다. */
   	public static List<String> generateCouponId(String date, int count) {

   		try {
   			List<String> coupons = Lists.newArrayList();

   			for (int i = 0; i < count; i++) {
   				SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
   				String randomNum = new Integer(prng.nextInt()).toString();
   				MessageDigest sha = MessageDigest.getInstance("SHA-1");
   				byte[] result = sha.digest(randomNum.getBytes());
   				String value = hexEncode(result);
   				String couponId = date + StringUtils.substring(value, 0, 10);
   				coupons.add(couponId);

   			}

   			return coupons;
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}

   	}

   	/* byte 배열을 hexa 데이터로 변환 한다. */
   	static private String hexEncode(byte[] aInput) {
   		StringBuilder result = new StringBuilder();
   		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
   		for (int idx = 0; idx < aInput.length; ++idx) {
   			byte b = aInput[idx];
   			result.append(digits[(b & 0xf0) >> 4]);
   			result.append(digits[b & 0x0f]);
   		}
   		return result.toString();
   	}

   	/**
   	 * 데이터 베이스 지원 여부
   	 * <p/>
   	 * <p/>
   	 * .
   	 *
   	 * @param dbType
   	 *            데이터베이스 타입
   	 * @throws Exception
   	 *             the exception
   	 * @since 2015
   	 */
   	private void checkSupported(DatabaseType dbType) {

   		// 현재 오라클 타입만 지원
   		if (DatabaseType.ORACLE != dbType) {

   			throw new NotSupportedException(null);
   		}
   	}

   	/**
   	 * 프런트에서 request 객체를 받으면 SystemPK를 생성 한다.
   	 *
   	 * <p/>
   	 *
   	 * <strong>선수조건</strong>
   	 * <p/>
   	 * HttpRequest 정보 <br/>
   	 * <p/>
   	 * <strong>입력 파라미터 정의</strong>
   	 * <p/>
   	 *
   	 * 예)
   	 * <p/>
   	 * HttpServletRequest request - 필수<br/>
   	 * <p/>
   	 * <strong>출력 파라미터 정의</strong>
   	 * <p/>
   	 *
   	 * SystemPK 오브젝트 - 필수<br/>
   	 *
   	 * @param request
   	 *            [설명]
   	 * @return the auto generator system pk
   	 * @throws Exception
   	 *             the exception
   	 * @since 2015
   	 */
   	public SystemPK getAutoGeneratorSystemPK(HttpServletRequest request) {

   		SystemPK pk = new SystemPK();

   		String mallId = getEnvValue(MALL_ID_SYSTEM_VARIABLE_ID);
   		pk.setMall(mallId);
   		pk.setLang(LocaleService.getCurrentLang(request));
   		pk.setDevice(getDevice(request));
   		pk.setApp(getApp(request));

   		MobileDevice device = getMobileDevice(request);
   		pk.setDeviceType(device.getDeviceType().name());
   		pk.setDevicePlatform(device.getDevicePlatform().name());

   		return pk;
   	}

   	/**
   	 * reqeust가 없는 경우 사용 ex) 배치모듈 등
   	 * @return SystemPK
   	 */
   	public SystemPK getAutoGeneratorSystemPK() {

   		SystemPK pk = new SystemPK();

   		String mallId = getEnvValue(MALL_ID_SYSTEM_VARIABLE_ID);
   		pk.setMall(mallId);
   		pk.setLang(lang);
   		pk.setDevice(device);
   		pk.setApp("");
   		pk.setDeviceType("");
   		pk.setDevicePlatform("");

   		return pk;
   	}

   	private MobileDevice getMobileDevice(HttpServletRequest request) {
   		return deviceResolver.resolveDevice(request);
   	}

   	/**
   	 * 접속 디바이스 판단.
   	 *
   	 * @param request
   	 *            HttpServletRequest
   	 * @return Device
   	 * @since 2015
   	 */
   	public String getDevice(HttpServletRequest request) {
   		String userAgent = request.getHeader("user-agent");
   		if (StringUtils.isEmpty(userAgent)) {
   			return "PC";
   		}
   		String deviceType = userAgent.toLowerCase();
   		String result = "PC";

   		String[] deviceStr = StringUtils.split(mobileDevice, "|");
   		String[] appStr = StringUtils.split(mobileApp, "|");

   		for (String mobile : deviceStr) {
   			if (deviceType.contains(mobile)) {
   				result = "MOBILE_WEB";
   				break;
   			}
   		}
   		for (String app : appStr) {
   			if (deviceType.contains(app)) {
   				result = "MOBILE_APP";
   				break;
   			}
   		}
   		return result;
   	}

   	public String getApp(HttpServletRequest request) {
   		String userAgent = request.getHeader("user-agent");
   		if (StringUtils.isEmpty(userAgent)) {
   			return "";
   		}
   		String result = "";
   		String deviceType = userAgent.toLowerCase();

   		String[] appStr = StringUtils.split(mobileApp, "|");

   		for (String app : appStr) {
   			if (deviceType.contains(app)) {
   				result = app;
   				break;
   			}
   		}
   		return result;
   	}

   	/* 임직원 인증 코드 발급 16자리*/
   	public static String generateEmpCrtfcCode(int cnt) {
   		try {
   			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
   			String randomNum = new Integer(prng.nextInt()).toString();
   			MessageDigest sha = MessageDigest.getInstance("SHA-1");
   			byte[] result = sha.digest(randomNum.getBytes());
   			String value = hexEncode(result);

   			return StringUtils.substring(value, 0, cnt);
   		} catch (NoSuchAlgorithmException e) {
   			throw new RuntimeException(e);
   		}
   	}
}
