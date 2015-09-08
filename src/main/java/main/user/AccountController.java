package main.user;

import main.security.SimpleAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User create(@RequestParam String emailAddress, @RequestParam String password) {
        return userService.create(emailAddress, password);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(
            HttpSession session,
            @RequestParam(value = "emailAddress") String emailAddress,
            @RequestParam(value = "password") String password) {
        User user = userService.get(emailAddress).orElseThrow(() -> new RuntimeException("No such password / username"));
        authenticate(password, user);
        session.setAttribute("authentication", new SimpleAuthentication(user));
    }

    private void authenticate(String password, User user) {
        if (!user.getSecret().equals(password)) {
            new RuntimeException("No such password / username");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public void logout(HttpSession session) {
        if (session != null) {
            LOGGER.debug("Logging out");
            session.removeAttribute("authentication");
            session.invalidate();
        }
    }
}
