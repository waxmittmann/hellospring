package main.user;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Function<String, Optional<User>> getByEmail
            = (emailAddress) -> userRepository.getFirstBy("emailAddress", emailAddress);

    @Transactional
    public User create(String emailAddress, String password) {
        List<User> usersWithEmail = userRepository.getBy("emailAddress", emailAddress);
        if (CollectionUtils.isEmpty(usersWithEmail)) {
            User newUser = new User(emailAddress, password);
            userRepository.persist(newUser);
            return newUser;
        } else {
            throw new RuntimeException("Cannot create user, user already exists");
        }
    }

    public Optional<User> get(String emailAddress) {
        return getByEmail.apply(emailAddress);
    }
}
