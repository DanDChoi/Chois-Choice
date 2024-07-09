package choi.choice.domain;

import lombok.Data;

@Data
public class SystemPK {
    /*몰 아이디*/
   	String mall;

   	/*언어 코드*/
   	String lang;

   	/*디바이스 코드*/
   	String device;

   	String deviceType; // NORMAL, TABLET, MOBILE

   	String devicePlatform; // IOS, ANDROID, UNKNOWN

   	/* 사이트 */
   	String site;

   	/*앱*/
   	String app;
}
