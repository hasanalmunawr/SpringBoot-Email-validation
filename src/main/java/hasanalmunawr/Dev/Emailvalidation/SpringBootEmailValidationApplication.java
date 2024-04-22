package hasanalmunawr.Dev.Emailvalidation;

import hasanalmunawr.Dev.Emailvalidation.Entity.Role;
import hasanalmunawr.Dev.Emailvalidation.repostiory.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SpringBootEmailValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailValidationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepo repo) {
		return args -> {
			if (repo.findByName("USER").isEmpty()) {
				repo.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}
}
