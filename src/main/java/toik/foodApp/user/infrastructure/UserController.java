package toik.foodApp.user.infrastructure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toik.foodApp.security.requests.RegistrationRequest;
import toik.foodApp.user.domain.UserFacade;
import toik.foodApp.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Class that represents api for user
 */
@RequestMapping("/api/users/")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserController {
  /**
   * variable that represents user db activities
   */
  UserFacade userFacade;

  /**
   * constructor for api
   * @param userFacade user facade
   */
  @Autowired
  UserController(UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  /**
   *
   * @param userIds user ids
   * @return http response with given user ids
   */
  @PostMapping("/user")
  public ResponseEntity<List<UserDto>> getUsers(@RequestBody List<Long> userIds) {
    return ResponseEntity.ok(userFacade.getUsersById(userIds));
  }

  /**
   *
   * @param userId user id
   * @return http response with given user
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<Optional<UserDto>> getUser(@PathVariable Long userId) {
    return ResponseEntity.ok(userFacade.findByUserId(userId));
  }

  /**
   *
   * @param employee employee
   * @return http response with saved employee
   */
  @PostMapping("/user/saveEmployee")
  public ResponseEntity<UserDto> saveEmployee(@RequestBody RegistrationRequest employee) {
    return ResponseEntity.ok(userFacade.registerEmployee(employee));
  }

}
