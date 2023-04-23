package toik.foodApp.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import toik.foodApp.user.dto.UserNotFoundException;

import java.util.Collection;
import java.util.Optional;


interface UserRepository extends JpaRepository<User, Long> {

  Collection<User> findAllByUserIdIn(Collection<Long> userId);

  Optional<User> findByUsername(String username);

  Optional<User> findByUserId(Long userId);


  default User findOneOrThrow(Long userId) {
    Optional<User> user = findById(userId);
    if(user.isPresent()) {
      throw new UserNotFoundException("User with id : " + userId + " not found");
    }
    return user.get();
  }
}
