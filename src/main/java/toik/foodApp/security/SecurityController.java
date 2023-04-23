package toik.foodApp.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import toik.foodApp.security.requests.LoginRequest;
import toik.foodApp.security.requests.RegistrationRequest;
import toik.foodApp.security.service.TokenService;
import toik.foodApp.user.domain.UserFacade;
import toik.foodApp.user.dto.UserDto;

import java.util.Optional;


@RequestMapping("/api/auth")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SecurityController {

  AuthenticationManager authenticationManager;
  UserFacade userFacade;
  TokenService tokenService;
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  SecurityController(UserFacade userFacade,
                     AuthenticationManager authenticationManager,
                     BCryptPasswordEncoder passwordEncoder,
                     TokenService tokenService) {
    this.userFacade = userFacade;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> registerUser(@RequestBody RegistrationRequest user) {
    UserDto registeredUser = userFacade.registerUser(user);

    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Optional<UserDto> userDto = userFacade.findByUsername(loginRequest.username());
    if(userDto.isPresent()) {
      boolean matchPassword = passwordEncoder.matches(loginRequest.password(), userDto.get().getPassword());

      if(matchPassword) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.username(), userDto.get().getPassword())
        );
        String token = tokenService.generateToken(auth);
        return ResponseEntity.ok(token);
      }
    }

    return ResponseEntity.status(401).body("Bad credentials");
  }
}
