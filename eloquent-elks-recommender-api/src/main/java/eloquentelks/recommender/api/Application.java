package eloquentelks.recommender.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Registers the REST Template in the DI Container
	 * @return RestTemplate used to access other REST Apis
	 */
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
