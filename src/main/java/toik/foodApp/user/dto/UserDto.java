package toik.foodApp.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDto {

  Long userId;

  String name;

  String surname;

  String username;

  String password;

  String email;

  String phoneNumber;

  String role;

}
