package toik.foodApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import toik.foodApp.security.RsaKeyProperties;
import toik.foodApp.security.SecurityConfig;
import toik.foodApp.security.requests.RegistrationRequest;
import toik.foodApp.user.domain.UserFacade;
import toik.foodApp.user.dto.UserDto;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan("toik.foodApp.*")
public class FoodApplication {

  public static void main(String[] args) {
    SpringApplication.run(FoodApplication.class, args);
  }

  @Bean
  CommandLineRunner run(UserFacade userFacade) {
    return args -> {
      userFacade.registerUser(new RegistrationRequest("Jan", "Kowalski", "jan123",
          "zaq1@WSX", "test@gmail.com", "123456789"));
    };
  }
}
