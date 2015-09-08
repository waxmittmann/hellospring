package main.security;

import main.user.User;

public class SimpleAuthentication {
    private final User user;

    public SimpleAuthentication(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
