package toik.foodApp.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import toik.foodApp.delivery.domain.Delivery;
import toik.foodApp.food.domain.Food;
import toik.foodApp.user.dto.UserDto;

import javax.persistence.*;
import java.util.List;


@Entity(name = "users")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  enum UserType {
    ADMIN,
    CLIENT,
    EMPLOYEE
  }

  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  Long userId;

  @Column(name="name")
  String name;

  @Column(name="surname")
  String surname;

  @Column(name="username", unique = true)
  String username;

  @Column(name="password")
  String password;

  @Column(name="email")
  String email;

  @Column(name="phone_number")
  String phoneNumber;

  @Column(name="user_type")
  @Enumerated(value = EnumType.STRING)
  UserType userType;


  UserDto fromDto() {

    return UserDto
        .builder()
        .userId(userId)
        .name(name)
        .surname(surname)
        .username(username)
        .password(password)
        .email(email)
        .phoneNumber(phoneNumber)
        .role(userType.name())
        .build();
  }

}
