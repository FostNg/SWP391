package group5.SE1863.DPSS_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("group5.SE1863.DPSS_backend.repository")
@EnableScheduling
public class DrugPreventionSupportSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugPreventionSupportSystemApplication.class, args);
	}

}
