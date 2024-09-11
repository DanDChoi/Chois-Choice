package choi.choice.framework.systems;

import lombok.Getter;
import org.springframework.core.env.Environment;

public enum Stage {
	LOCAL("L", "local"),
    DEV("D", "dev"),
    STAGING("S", "stg"),
    PRODUCTION("P", "prod");

	@Getter
	private String symbol;

	@Getter
	private String springProfileName;

	Stage(String symbol, String springProfileName) {
		this.symbol = symbol;
		this.springProfileName = springProfileName;
	}

	public static Stage decideStage(Environment env) {
		for (Stage stage : values()) {
			if (env.acceptsProfiles(stage.getSpringProfileName())) {
				return stage;
			}
		}
		return LOCAL;
	}
}
