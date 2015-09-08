package main.user;

import main.persistence.SimpleRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepo")
public class UserRepository extends SimpleRepository<Long, User> {
    public UserRepository() {
        super(User.class);
    }
}
