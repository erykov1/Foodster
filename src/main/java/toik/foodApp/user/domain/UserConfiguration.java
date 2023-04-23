package toik.foodApp.user.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserConfiguration {

  public UserFacade userFacade() {

    return UserFacade.builder()
        .userRepository(new InMemoryUserRepository())
        .passwordEncoder(new BCryptPasswordEncoder())
        .build();
  }

  @Bean
  public UserFacade userFacade(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    return UserFacade.builder()
        .passwordEncoder(passwordEncoder)
        .userRepository(userRepository)
        .build();
  }
}
