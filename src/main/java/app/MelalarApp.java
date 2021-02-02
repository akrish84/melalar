package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.repository.authentication.UserRepository;


@SpringBootApplication
//@EnableJpaRepositories(basePackages = "app.repository")
public class MelalarApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(MelalarApp.class, args);
		System.out.println("Done");
	}

}
