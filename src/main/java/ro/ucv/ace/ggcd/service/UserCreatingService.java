package ro.ucv.ace.ggcd.service;

import org.springframework.stereotype.Service;
import ro.ucv.ace.ggcd.model.User;
import ro.ucv.ace.ggcd.repository.UserRepository;

@Service
public class UserCreatingService {

  private static int numberOfUsersCreated = 0;

  private final UserRepository userRepository;

  public UserCreatingService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(String name) {

    User user = new User();
    user.setUuid(++numberOfUsersCreated);
    user.setName(name);
    return userRepository.save(user);
  }
}
