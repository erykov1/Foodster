package toik.foodApp.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImplementation implements UserDetailsService {

  UserRepository userRepository;

  @Autowired
  UserDetailServiceImplementation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Can not find user with such username: " + username));

    return CustomUserDetailsService.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .password("{noop}" + user.getPassword())
        .email(user.getEmail())
        .role(user.getUserType())
        .build();
  }
}
