package toik.foodApp.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import toik.foodApp.mail.domain.EmailService;
import toik.foodApp.security.requests.RegistrationRequest;
import toik.foodApp.user.dto.AlreadyTakenException;
import toik.foodApp.user.dto.UserDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Builder
public class UserFacade {

  UserRepository userRepository;

  BCryptPasswordEncoder passwordEncoder;


  public UserDto registerUser(RegistrationRequest user) {

    Optional<UserDto> userDto = findByUsername(user.getUsername());
    if(userDto.isPresent()) {
      throw new AlreadyTakenException("Username is already taken");
    }

    User saveUser = User
        .builder()
        .name(user.getName())
        .surname(user.getSurname())
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .userType(User.UserType.CLIENT)
        .build();

    return userRepository.save(saveUser).fromDto();
  }

  public UserDto registerEmployee(RegistrationRequest employee) {

    Optional<UserDto> userDto = findByUsername(employee.getUsername());
    if(userDto.isPresent()) {
      throw new AlreadyTakenException("Username is already taken");
    }

    User saveEmployee = User
        .builder()
        .name(employee.getName())
        .surname(employee.getSurname())
        .username(employee.getUsername())
        .password(passwordEncoder.encode(employee.getPassword()))
        .email(employee.getEmail())
        .phoneNumber(employee.getPhoneNumber())
        .userType(User.UserType.EMPLOYEE)
        .build();

    return userRepository.save(saveEmployee).fromDto();
  }

  public List<UserDto> getUsersById(Collection<Long> users) {
    return userRepository.findAllByUserIdIn(users)
        .stream()
        .map(User::fromDto)
        .collect(Collectors.toList());
  }

  public Optional<UserDto> findByUserId(Long userId) {
    return userRepository.findById(userId).map(User::fromDto);
  }

  public Optional<UserDto> findByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(User::fromDto);
  }

  public List<UserDto> findAllUsers() {

    return userRepository.findAll()
        .stream()
        .map(User::fromDto)
        .collect(Collectors.toList());
  }

}
