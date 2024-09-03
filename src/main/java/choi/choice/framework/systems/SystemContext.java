package choi.choice.framework.systems;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Getter
@Setter
@Slf4j
public class SystemContext implements InitializingBean {

    @Autowired
           Environment env;

   	@Autowired
   	SystemClock clock;

   	public static SystemContext instance;

   	/**
   	 * 현재 실행 되고 있는 어플리케이션이 PC, MB 등 어떤것인가?
   	 */
   	ApplicationType appType = ApplicationType.UNKNOWN;

   	/**
   	 * 현재 실행되고 있는 서버의 스테이지는 운영인가? 개발서버인가?
   	 */
   	Stage stage = Stage.LOCAL;

   	/**
   	 * 현재 실행되고 있는 WAS의 인스턴스는 어떤 것인가?
   	 */
   	WasInstance wasInstance = WasInstance.M1;

   	AtomicLong seq = new AtomicLong(0);

   	Lane lane = Lane.LANE1;

   	static ThreadLocal<Language> currentRequestlanguageLocal = new ThreadLocal<>();

   	static Random random = new Random();

   	@Override
   	public void afterPropertiesSet() throws Exception {
   		appType = ApplicationType.decideApplicationType(env);
   		stage = Stage.decideStage(env);
   		wasInstance = WasInstance.decideWasInstance(env);
   		seq = new AtomicLong(0);
   		instance = this;
   		lane = Lane.decideLane(stage, wasInstance);

   		log.debug("System Context appType:{} stage:{} wasInstance:{}", appType, stage, wasInstance);
   	}

   	public static boolean isLocal() {
   		return instance.stage == Stage.LOCAL;
   	}

   	public String nextRequestId() {
   		return appType.getSymbol() + wasInstance.getSymbol() + nextUid();
   	}

   	public String nextUid() {

   		long now = clock.currentTimeMillis();
   		StringBuilder builder = new StringBuilder();
   		builder.append(now);
   		for(int i=0; i<10;i++) {
   			builder.append(random.nextInt(10));
   		}
   		return builder.toString();
   	}

   	public static String generateRequestId() {
   		return instance.nextRequestId();
   	}

   	public void setCurrentRequestLanguage(Language language) {
   		currentRequestlanguageLocal.set(language);
   	}

   	public Language getCurrentRequestLanguage() {
   		return currentRequestlanguageLocal.get();
   	}
}
