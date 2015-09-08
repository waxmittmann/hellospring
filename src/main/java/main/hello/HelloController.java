package main.hello;

import main.security.SimpleAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/hellos")
public class HelloController {
    private static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Hello> getHellos() {
        LOGGER.info("Called getHellos");
        return helloService.getHellos();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public HttpStatus addHello(HttpSession session, @RequestParam String message) {
        LOGGER.info("Created hello with message " + message);

        if (session.getAttribute("authentication") != null) {
            LOGGER.debug("Creating authenticated hello");
            SimpleAuthentication auth = (SimpleAuthentication) session.getAttribute("authentication");
            helloService.createAuthenticatedHello(message, auth.getUser().getEmailAddress());
        } else {
            LOGGER.debug("Creating anonymous hello");
            helloService.createAnonymousHello(message);
        }

        return HttpStatus.OK;
    }

}
