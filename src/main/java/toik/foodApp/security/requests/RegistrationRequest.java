package toik.foodApp.security.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationRequest {

  String name;

  String surname;

  String username;

  String password;

  String email;

  String phoneNumber;

}
