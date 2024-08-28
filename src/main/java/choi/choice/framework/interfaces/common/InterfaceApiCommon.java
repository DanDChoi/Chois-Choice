package choi.choice.framework.interfaces.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class InterfaceApiCommon {
    public static final String GET = "GET";
    public static final String POST = "POST";

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
}
