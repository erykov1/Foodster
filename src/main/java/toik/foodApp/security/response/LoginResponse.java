package toik.foodApp.security.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that represents login response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
  /**
   * variable that represents user id
   */
  long userId;
  /**
   * variable that represents username
   */
  String username;
  /**
   * variable that represents user role
   */
  String role;
}
