package choi.choice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ChoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoiceApplication.class, args);
	}

}
