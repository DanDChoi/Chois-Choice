package choi.choice.framework.interfaces.common;

import choi.choice.framework.adapter.AdapterHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class InterfaceApiCommon {
    public static final String GET = "GET";
    public static final String POST = "POST";
    private @Value("${base.interface.server.url}") String interfaceServerUrl;
    private @Value("${base.interface.server.public.url}") String interfaceServerPublicUrl;
    private @Value("${base.interface.server.erp.url}") String interfaceServerErpUrl;
    private @Value("${base.interface.connection.timeout:30000}") int connect;
    private @Value("${base.interface.read.timeout:30000}") int read;
    private @Value("${base.interface.socket.connection.timeout:30000}") int socketConnect;
    private @Value("${base.interface.socket.read.timeout:30000}") int socketRead;

    @PostConstruct
    public void init() {log.info("interfaceServerUrl : {}");}

    /**
     * interface server url 을 가져온다.
     *
     * @return interface server url
     */
    public String getInterfaceServerUrl() {
        return interfaceServerUrl;
    }

    /**
     * interface server public url 을 가져온다.
     *
     * @return interface server public url
     */
    public String getInterfaceServerPublicUrlUrl() {
        return interfaceServerPublicUrl;
    }

    public String getInterfaceServerErpUrl() {
        return interfaceServerErpUrl;
    }

    /**
     * RequestId 를 가져 온다.
     *
     * @return request id
     */
    public String getRequestId() {
        String uuid = UUID.randomUUID().toString().replace("-", "") + Calendar.getInstance().getTimeInMillis();
        if (uuid.length() > 50) {
            uuid = uuid.substring(0, 50);
        }
        log.info("getRequestId : {}", uuid);
        return uuid;
    }

    /**
     * 현재 일자와 시간을 string format 으로 가져 온다.
     *
     * @return current date
     */
    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    /**
     * 현재 일자를 string format 으로 가져 온다.
     *
     * @return short current date
     */
    public String getShortCurrentDate() {
        log.debug("{}", this.getClass().getName() + ".getShortCurrentDate");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }

    /**
     * '2010-10-22 15:20:46' 포멧의 string 을 '2010-10-22 15:20:46' 포멧의 date 로 변환해서 반환
     * 한다.
     *
     * @param stringDate the string date
     * @return date from string
     * @throws ParseException the parse exception
     */
    public Date getDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.parse(stringDate);
    }

    /**
     * interface server 와 RESTful 통신한다.
     *
     * @param inputJson     the input json
     * @param serverUrl     the server url
     * @param adapterHeader the adapter header
     * @return string string
     * @throws Exception the exception
     */
    public String requestToServer(String inputJson, String serverUrl, AdapterHeader adapterHeader)  throws Exception {
        if ( serverUrl.contains("https:") ) return https(inputJson , serverUrl , adapterHeader , false );
        else return http(inputJson , serverUrl , adapterHeader , false);
    }

    /**
     * interface server 와 RESTful 통신한다. (EC 모니터 전)
     *
     * @param inputJson     the input json
     * @param serverUrl     the server url
     * @param adapterHeader the adapter header
     * @param isEcMonitor the adapter header
     * @return string string
     * @throws Exception the exception
     */
    public String requestToServerEcMonitor(String inputJson, String serverUrl, AdapterHeader adapterHeader, boolean isEcMonitor)  throws Exception {
    	if ( serverUrl.contains("https:") ) return https(inputJson , serverUrl , adapterHeader , false , true);
    	else return http(inputJson , serverUrl , adapterHeader , false, true);
    }
    /**
     * Request to server utf 8 string.
     *
     * @param inputJson     the input json
     * @param serverUrl     the server url
     * @param adapterHeader the adapter header
     * @return the string
     * @throws Exception the exception
     */
    public String requestToServerUTF8(String inputJson, String serverUrl, AdapterHeader adapterHeader)  throws Exception {
        if ( serverUrl.contains("https:") ) return https(inputJson , serverUrl , adapterHeader , true );
        else return http(inputJson , serverUrl , adapterHeader , true);
    }

    private String http(String inputJson, String serverUrl, AdapterHeader adapterHeader , boolean isUTF8 )  throws Exception {
        log.info("{}", this.getClass().getName() + ".requestToServer" + ( isUTF8 ? "UTF8" : "" ) );
        log.info("> adapterHeader : {}", adapterHeader);
        log.info("> inputJson : {}", inputJson);
        log.info("> serverUrl : {}", serverUrl);

        String result = "";

        try {
            URL postUrl = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
            connection.setConnectTimeout(connect);
            connection.setReadTimeout(read);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(POST);
            connection.setRequestProperty("Content-Type", "application/json");

            /**
             * Request Header setup.
             */

            if (ExecutionContext.currentRequestInfo() != null
                    && ExecutionContext.currentRequestInfo().getRequestId() != null) {
                String actionId = ExecutionContext.currentRequestInfo().getRequestId();
                connection.setRequestProperty("ACTION_ID", actionId);
            }
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
            if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
                connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
            }

            OutputStream os = connection.getOutputStream();
            os.write(isUTF8 ? inputJson.getBytes("UTF-8") : inputJson.getBytes());
            os.flush();
            os.close();

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
                result = getInterfaceErrorCode(connection);
            } else {
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                    InputStream in = connection.getInputStream();
                    BufferedReader reader;
                    if ( isUTF8 ) reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192);
                    else reader = new BufferedReader(new InputStreamReader(in), 8192);
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                            log.debug(builder.toString());
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }
                    result = builder.toString().replace("\n", "");
                } else {
                    result = responseCode + "";
                }
            }
            connection.disconnect();

        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }

        log.info("> common result : {}", result);

        return result;
    }

    private String http(String inputJson, String serverUrl, AdapterHeader adapterHeader , boolean isUTF8 , boolean isEcMonitor)  throws Exception {
    	log.info("{}", this.getClass().getName() + ".requestToServer" + ( isUTF8 ? "UTF8" : "" ) );
    	log.info("> adapterHeader : {}", adapterHeader);
    	log.info("> inputJson : {}", inputJson);
    	log.info("> serverUrl : {}", serverUrl);

    	String result = "";

    	try {
    		URL postUrl = new URL(serverUrl);
    		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
    		connection.setConnectTimeout(connect);
    		if(isEcMonitor) {
    			connection.setReadTimeout(300000);
    		}else {
    			connection.setReadTimeout(read);
    		}
    		connection.setDoOutput(true);
    		connection.setInstanceFollowRedirects(false);
    		connection.setRequestMethod(POST);
    		connection.setRequestProperty("Content-Type", "application/json");

    		/**
    		 * Request Header setup.
    		 */

    		if (ExecutionContext.currentRequestInfo() != null
    				&& ExecutionContext.currentRequestInfo().getRequestId() != null) {
    			String actionId = ExecutionContext.currentRequestInfo().getRequestId();
    			connection.setRequestProperty("ACTION_ID", actionId);
    		}
    		connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
    		connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
    		connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
    		connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
    		if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
    			connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
    		}

    		OutputStream os = connection.getOutputStream();
    		os.write(isUTF8 ? inputJson.getBytes("UTF-8") : inputJson.getBytes());
    		os.flush();
    		os.close();

    		if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
    			result = getInterfaceErrorCode(connection);
    		} else {
    			int responseCode = connection.getResponseCode();

    			if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
    				InputStream in = connection.getInputStream();
    				BufferedReader reader;
    				if ( isUTF8 ) reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192);
    				else reader = new BufferedReader(new InputStreamReader(in), 8192);
    				StringBuilder builder = new StringBuilder();
    				String line = null;

    				try {
    					while ((line = reader.readLine()) != null) {
    						builder.append(line).append("\n");
    						log.error(builder.toString());
    					}
    				} catch (IOException e) {
    					log.error("", e);
    				}
    				result = builder.toString().replace("\n", "");
    			} else {
    				result = responseCode + "";
    			}
    		}
            connection.disconnect();
    	} catch (Exception e) {
    		StringWriter error = new StringWriter();
    		e.printStackTrace(new PrintWriter(error));
    		log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
    		throw e;
    	}

    	log.info("> common result : {}", result);

    	return result;
    }

    private String https(String inputJson, String serverUrl, AdapterHeader adapterHeader , boolean isUTF8 ) throws Exception {
        log.info("{}", this.getClass().getName() + ".requestToServer" + ( isUTF8 ? "UTF8" : "" ) + "_ssl" );
        log.info("> inputJson : {}", inputJson);
        log.info("> serverUrl : {}", serverUrl);

        String result = "";

        try {
            URL postUrl = new URL(serverUrl);
            HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
            connection.setConnectTimeout(connect);
            connection.setReadTimeout(read);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(POST);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setHostnameVerifier( ( hostname , session ) -> true );

            //SSLContext context = SSLContext.getInstance("TLSv1.2");
            //context.init(null, getTrustManagers() , new SecureRandom());
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null , null);
            connection.setSSLSocketFactory(context.getSocketFactory());

            /**
             * Request Header setup.
             */
            if (ExecutionContext.currentRequestInfo() != null
                    && ExecutionContext.currentRequestInfo().getRequestId() != null) {
                String actionId = ExecutionContext.currentRequestInfo().getRequestId();
                connection.setRequestProperty("ACTION_ID", actionId);
            }
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
            if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
                connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
            }
            if ( StringUtils.isNotEmpty( adapterHeader.getAuthorization() ) ) {
                connection.setRequestProperty("Authorization", adapterHeader.getAuthorization());
            }

            connection.connect();
            if (  log.isDebugEnabled() ) {
                log.debug("connection cipherSuite = {} " , connection.getCipherSuite() );
            }

            OutputStream os = connection.getOutputStream();
            os.write(isUTF8 ? inputJson.getBytes("UTF-8") : inputJson.getBytes());
            os.flush();
            os.close();

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
                result = getInterfaceErrorCode(connection);
            } else {
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = isUTF8 ?
                            new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192) :
                            new BufferedReader(new InputStreamReader(in), 8192);;
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                            log.error(builder.toString());
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    result = builder.toString().replace("\n", "");
                } else {
                    result = responseCode + "";
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }

        log.info("> common result : {}", result);

        return result;
    }

    private String https(String inputJson, String serverUrl, AdapterHeader adapterHeader , boolean isUTF8, boolean isEcMonitor ) throws Exception {
    	log.info("{}", this.getClass().getName() + ".requestToServer" + ( isUTF8 ? "UTF8" : "" ) + "_ssl" );
    	log.info("> inputJson : {}", inputJson);
    	log.info("> serverUrl : {}", serverUrl);

    	String result = "";

    	try {
    		URL postUrl = new URL(serverUrl);
    		HttpsURLConnection connection = (HttpsURLConnection) postUrl.openConnection();
    		if(isEcMonitor) {
                connection.setConnectTimeout(120000);
    			connection.setReadTimeout(120000);
    		}else {
                connection.setConnectTimeout(connect);
    			connection.setReadTimeout(read);
    		}
    		connection.setDoOutput(true);
    		connection.setInstanceFollowRedirects(false);
    		connection.setRequestMethod(POST);
    		connection.setRequestProperty("Content-Type", "application/json");
    		connection.setHostnameVerifier( ( hostname , session ) -> true );

    		//SSLContext context = SSLContext.getInstance("TLSv1.2");
    		//context.init(null, getTrustManagers() , new SecureRandom());
    		SSLContext context = SSLContext.getInstance("TLS");
    		context.init(null, null , null);
    		connection.setSSLSocketFactory(context.getSocketFactory());

    		/**
    		 * Request Header setup.
    		 */
    		if (ExecutionContext.currentRequestInfo() != null
    				&& ExecutionContext.currentRequestInfo().getRequestId() != null) {
    			String actionId = ExecutionContext.currentRequestInfo().getRequestId();
    			connection.setRequestProperty("ACTION_ID", actionId);
    		}
    		connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
    		connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
    		connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
    		connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
    		if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
    			connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
    		}
    		if ( StringUtils.isNotEmpty( adapterHeader.getAuthorization() ) ) {
    			connection.setRequestProperty("Authorization", adapterHeader.getAuthorization());
    		}

    		connection.connect();
    		if (  log.isDebugEnabled() ) {
    			log.error("connection cipherSuite = {} " , connection.getCipherSuite() );
    		}

    		OutputStream os = connection.getOutputStream();
    		os.write(isUTF8 ? inputJson.getBytes("UTF-8") : inputJson.getBytes());
    		os.flush();
    		os.close();

    		if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
    			result = getInterfaceErrorCode(connection);
    		} else {
    			int responseCode = connection.getResponseCode();

    			if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
    				InputStream in = connection.getInputStream();
    				BufferedReader reader = isUTF8 ?
    						new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192) :
    							new BufferedReader(new InputStreamReader(in), 8192);;
    							StringBuilder builder = new StringBuilder();
    							String line = null;

    							try {
    								while ((line = reader.readLine()) != null) {
    									builder.append(line).append("\n");
    									log.error(builder.toString());
    								}
    							} catch (IOException e) {
    								log.error("", e);
    							}

    							result = builder.toString().replace("\n", "");
    			} else {
    				result = responseCode + "";
    			}
    		}
    	} catch (Exception e) {
    		StringWriter error = new StringWriter();
    		e.printStackTrace(new PrintWriter(error));
    		log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
    		//throw e;
    	}

    	log.info("> common result : {}", result);

    	return result;
    }

    /**
     * interface server 와 RESTful 통신한다. inputstream 도 UTF-8 처리
     *
     * @param parameterMap  the url Parameter Map
     * @param serverUrl     the server url
     * @param adapterHeader the adapter header
     * @return string string
     * @throws Exception the exception
     */
    public String requestToServerForm(Map<String,Object> parameterMap , String serverUrl, AdapterHeader adapterHeader) throws Exception {

        log.info("{}", this.getClass().getName() + ".requestToServer");
        log.info("> serverUrl : {}", serverUrl);
        log.info("> paramter map : {}", parameterMap);

        String hyphen = "--";
        String boundary = "hfashionmall";
        String feed = "\r\n";
        String result = "";

        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(connect);
            connection.setReadTimeout(read);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(POST );
            connection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);

            if (ExecutionContext.currentRequestInfo() != null
                    && ExecutionContext.currentRequestInfo().getRequestId() != null) {
                String actionId = ExecutionContext.currentRequestInfo().getRequestId();
                connection.setRequestProperty("ACTION_ID", actionId);
            }
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
            if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
                connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
            }

            OutputStream out = connection.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out , "UTF-8") , true);
            for ( Map.Entry<String, Object> entry : parameterMap.entrySet() ) {
                pw.append(hyphen).append(boundary);
                pw.append(feed).append("Content-Disposition: form-data; name=\"" + entry.getKey() +"\"").append(feed);
                pw.append(feed).append(JsonService.marshalling(entry.getValue())).append(feed);
            }
            pw.append(hyphen).append(boundary).append(hyphen).append(feed);
            pw.flush();
            pw.close();

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
                result = getInterfaceErrorCode(connection);
            } else {
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192);
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    result = builder.toString().replace("\n", "");
                } else {
                    result = responseCode + "";
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }
        log.info("> common result : {}", result);
        return result;
    }

    /**
     * interface server 와 RESTful 통신한다. inputstream 도 UTF-8 처리
     *
     * @param parameterMap  the url Parameter Map
     * @param serverUrl     the server url
     * @param adapterHeader the adapter header
     * @return string string
     * @throws Exception the exception
     */
    public String requestToServerParam(Map<String,Object> parameterMap , String serverUrl, AdapterHeader adapterHeader , boolean... isGet) throws Exception {

        log.info("{}", this.getClass().getName() + ".requestToServer");
        log.info("> serverUrl : {}", serverUrl);
        log.info("> paramter map : {}", parameterMap);

        String result = "";
        serverUrl += ( parameterMap.entrySet().size() > 0)  ? "?" : "";
        boolean isGetMethod = isGet.length > 0 && isGet[0] ;

        StringBuffer parameters = new StringBuffer();
        String test = "";
        for ( Map.Entry<String, Object> entry : parameterMap.entrySet() ) {
            parameters.append(entry.getKey()).append("=").append(entry.getValue());
            if (parameterMap.entrySet().size() > 0){
                parameters.append("&");
            }
        }

        try {
            URL url = new URL(serverUrl + parameters.toString() );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(connect);
            connection.setReadTimeout(read);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(isGetMethod ? GET : POST );
            connection.setRequestProperty("Content-Type", "application/json");

            if (ExecutionContext.currentRequestInfo() != null
                    && ExecutionContext.currentRequestInfo().getRequestId() != null) {
                String actionId = ExecutionContext.currentRequestInfo().getRequestId();
                connection.setRequestProperty("ACTION_ID", actionId);
            }
            connection.setRequestProperty("REQUEST_ID", adapterHeader.getRequestId());
            connection.setRequestProperty("MALL_ID", adapterHeader.getMallId());
            connection.setRequestProperty("LANG_CD", adapterHeader.getLangCd());
            connection.setRequestProperty("DVC_CD", adapterHeader.getDvcCd());
            if ( StringUtils.isNotEmpty( adapterHeader.getCallerId() ) ) {
                connection.setRequestProperty("CALLER_ID" , adapterHeader.getCallerId());
            }
            if( StringUtils.isNotEmpty( adapterHeader.getAuthorization()) ) {
                connection.setRequestProperty("Authorization", adapterHeader.getAuthorization());
            }

            if (connection.getHeaderField("INTERFACE_EXCEPTION_CODE") != null) {
                result = getInterfaceErrorCode(connection);
            } else {
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8192);
                    StringBuilder builder = new StringBuilder();
                    String line = null;

                    try {
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    result = builder.toString().replace("\n", "");
                } else {
                    result = responseCode + "";
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.error("> Failure message : {}", this.getClass().getName() + " : " + error.toString());
            throw e;
        }
        log.info("> common result : {}", result);
        return result;
    }

    /**
     * Request to server socket string.
     *
     * @param host      the host
     * @param parameter the parameter
     * @return the string
     * @throws Exception the exception
     */
    public String requestToServerSocket(HostSocket host , Object... parameter) throws Exception {
        String result = "";
        SocketFactory socketFactory;
        SSLSocketFactory sslSocketFactory;
        Socket socket = null;
        SSLSocket sslSocket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        String msg = "";

        if (  StringUtils.isNotEmpty( msg = validateObject(host) ) ) {
            return msg;
        }

        try {
            if (host.isSSL()) {
                sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                sslSocket = (SSLSocket) sslSocketFactory.createSocket();
                sslSocket.setSoTimeout(socketRead);
                sslSocket.connect(new InetSocketAddress(host.getIp(), Integer.valueOf(host.getPort())), socketConnect);
                writer = new PrintWriter(sslSocket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream(), host.getCharset()));
            } else {
                socketFactory = SocketFactory.getDefault();
                socket = socketFactory.createSocket();
                socket.setSoTimeout(socketRead);
                socket.connect(new InetSocketAddress(host.getIp(), Integer.valueOf(host.getPort())), socketConnect);
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), host.getCharset()));
            }
        } catch (UnknownHostException e1) {
            result = "Unknown Host Exception raised : " + e1.getMessage() + "\n";
            return result;
        } catch (IOException e2) {
            result = "I/O Exception raised : " + e2.getMessage() + "\n";
            return result;
        } catch (Exception e3) {
            result = "Exception raised : " + e3.getMessage() + "\n";
            return result;
        }

        try {
            StringBuffer sb = new StringBuffer();
            if ( parameter.length > 1)  {
                sb.append(multipleParameterStr(parameter));
            } else {
                if (  StringUtils.isNotEmpty( msg = validateObject(parameter[0]) ) ) {
                    return msg;
                }
                if ( parameter[0] instanceof ArrayList) {
                    for ( Object object : (ArrayList) parameter[0] ) {
                        sb.append(singleParameterStr(object));
                    }
                } else if ( parameter[0] instanceof String ) {
                    sb.append(parameter[0]);
                } else {
                    sb.append(singleParameterStr(parameter[0]));
                }
            }
            if ( StringUtils.isNotEmpty(sb.toString())) {
                writer.write(sb.toString());
                writer.flush();
                String response = "";
                while ((response = reader.readLine()) != null) {
                    result += response + "\n";
                }
            }
            return result;
        } catch (Exception e) {
            result = "Socket Connect Error : " + e.getMessage() + "\n";
            return result;
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
                if (sslSocket != null) sslSocket.close();
                if (socket != null) socket.close();
            } catch (Exception ef) {}
        }
    }

    private String singleParameterStr(Object object) throws IllegalAccessException {
        StringBuffer sb = new StringBuffer();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (ArrayList.class.getName().equals(field.getType().getName())) {
                field.setAccessible(true);
                sb.append(getRepeatSyntax((ArrayList) field.get(object)));
            } else {
                field.setAccessible(true);
                sb.append(field.get(object));
            }
        }
        return sb.toString();
    }

    private String multipleParameterStr(Object[] objects) throws IllegalAccessException {
        StringBuffer sb = new StringBuffer();
        for (Object object : objects) {
            if (object instanceof ArrayList)  sb.append(getRepeatSyntax((ArrayList) object));
            else sb.append(object.toString());
        }
        return sb.toString();
    }

    private String getRepeatSyntax(ArrayList list) throws IllegalAccessException {
        StringBuffer sb = new StringBuffer();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Object object = iter.next();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                sb.append(field.get(object));
            }
        }
        return sb.toString();
    }

    private String validateObject(Object object) throws Exception {
        Set<ConstraintViolation<Object>> list = Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        if (list != null) {
            for (ConstraintViolation<Object> c : list) {
                if (StringUtils.isNotEmpty(c.getMessage())) return c.getMessage();
            }
        }
        return null;
    }

    /**
     * 현재 시간을 millisecond 의 string 포멧으로 반환한다.
     *
     * @return current millis time
     */
    public String getCurrentMillisTime() {
        log.debug("{}.getCurrentMillisTime", InterfaceApiCommon.class);

        Calendar calendar = Calendar.getInstance();

        return calendar.getTimeInMillis() + "";
    }

    /**
     * 입력받은 millisecond 로 "yyyy-MM-dd HH:mm:ss.SSS" 포멧의 date 를 가져온다.
     *
     * @param millisDate [설명]
     * @return String [설명]
     * @throws ParseException the parse exception
     * @since 2015
     */
    public String getDateFromMillis(String millisDate) throws ParseException {
        log.debug("{}.getDateFromMillis", InterfaceApiCommon.class);

        long millisDateLong = Long.parseLong(millisDate);
        Date date = new Date(millisDateLong);

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        return formatter.format(date);
    }

    /**
     * 입력받은 2개의 string 타입의 날짜로 시간계산을 해서 string 타입의 초단위로 반환한다.
     *
     * @param start  [설명]
     * @param finish [설명]
     * @return String [설명]
     * @throws ParseException the parse exception
     * @since 2015
     */
    public String getProcessDateTime(String start, String finish) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date startDateTime = transFormat.parse(start);
        long startTime = startDateTime.getTime();

        Date finishDateTime = transFormat.parse(finish);
        long finishTime = finishDateTime.getTime();

        return ((finishTime - startTime) / 1000.0f) + "";
    }

    /**
     * 입력받은 2개의 string 타입의 날짜로 시간계산을 해서 long 타입의 초단위로 반환한다.
     *
     * @param start  [설명]
     * @param finish [설명]
     * @return Long [설명]
     * @throws ParseException the parse exception
     * @since 2015
     */
    public long getElapseDatetime(String start, String finish) throws ParseException {
        log.debug("{}.getElapseDatetime", InterfaceApiCommon.class);

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date startDateTime = transFormat.parse(start);
        long startTime = startDateTime.getTime();

        Date finishDateTime = transFormat.parse(finish);
        long finishTime = finishDateTime.getTime();

        return (long) ((finishTime - startTime) / 1000.0f);
    }

    /**
     * 서버 기준 현재 월 리턴.
     *
     * @param preMon [설명]
     * @return String [설명]
     * @since 2015
     */
    public String getSvrPreMonth(int preMon) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, preMon);
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyyMMdd");
        return defaultFormat.format(cal.getTime());
    }

    /**
     * 서버 기준 현재 일자 리턴
     *
     * @return svr current date
     */
    public String getSvrCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyyMMdd");
        return defaultFormat.format(cal.getTime());
    }

    /**
     * 입력받은 두 시간의 차이를 구해서 반환한다.
     * 1. 입력받은 두 시간의 순서는 상관없다. 2. 시차에 의해서 client 가 server 보다 시간이 빠를수도 늦을수도 있다.
     * 2. 시간을 long 으로 변환한뒤 큰수에서 작은수의 차를 구한다.
     *
     * @param start  [설명]
     * @param finish [설명]
     * @return String [설명]
     * @throws ParseException the parse exception
     * @since 2015
     */
    public String getElapsedTime(String start, String finish) throws ParseException {

        log.debug("{}.getElapsedTime", InterfaceApiCommon.class);

        String result = "";

        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date startDateTime = transFormat.parse(start);
        long startTime = startDateTime.getTime();

        Date finishDateTime = transFormat.parse(finish);
        long finishTime = finishDateTime.getTime();

        if (finishTime > startTime) {
            result = ((finishTime - startTime) / 1000.0f) + "";
        } else {
            result = ((startTime - finishTime) / 1000.0f) + "";
        }

        return result;
    }

    /**
     * DTO object를 hashMap으로 컨번트
     *
     * @param vo the vo
     * @return HashMap hash map
     * @wroter Jang
     */
    public HashMap<String, Object> toHashMap(Object vo) {
        // 해당 객체를 받는다.
        Class<?> cls = vo.getClass();

        // 리턴할 HashMap 생성
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        String key = "";
        Object value = null;
        for (Field field : cls.getDeclaredFields()) {
            // 접근 가능하도록 설정
            field.setAccessible(true);
            // 키값을 받는다.
            key = field.getName();

            try {
                // Value 값을 받는다.
                value = field.get(vo);
            } catch (IllegalArgumentException ex) {
                log.error("{}", ex.getMessage());
            } catch (IllegalAccessException ex) {
                log.error("{}", ex.getMessage());
            }
            // HashMap에 넣는다.
            if (value != null && !value.equals(""))
                hashMap.put(key, value);
        }

        return hashMap;
    }

    private String getInterfaceErrorCode(HttpURLConnection connection) throws UnsupportedEncodingException {
        String errorMessage = URLDecoder.decode(connection.getHeaderField("INTERFACE_EXCEPTION_MESSAGE"), "UTF-8");

        log.error("> INTERFACE_EXCEPTION_CODE    : {}", connection.getHeaderField("INTERFACE_EXCEPTION_CODE"));
        log.error("> INTERFACE_EXCEPTION_MESSAGE : {}", errorMessage);

        return "EXCEPTION" + ":" + connection.getHeaderField("INTERFACE_EXCEPTION_CODE") + ":" + errorMessage;
    }

/*
    private TrustManager[] getTrustManagers(){
        return new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String type) {}
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String type) {
                    try {
                        KeyStore keyStore = KeyStore.getInstance("JKS");
                        String cacertPath = System.getProperty("java.home") + "/lib/security/cacerts";
                        keyStore.load(new FileInputStream(cacertPath), "key".toCharArray());
                        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init(keyStore);
                        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                        ((X509TrustManager) trustManagers[0]).checkServerTrusted(x509Certificates, type);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() { return null; }
            }
        };
    }
*/

}
