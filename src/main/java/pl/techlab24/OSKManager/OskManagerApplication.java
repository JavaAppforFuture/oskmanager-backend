package pl.techlab24.OSKManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OskManagerApplication.class, args);
    }

}
