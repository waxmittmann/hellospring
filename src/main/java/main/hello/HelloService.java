package main.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {
    private static Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private HelloRepository helloRepository;

    public List<Hello> getHellos() {
        return helloRepository.getAll();
    }

    public void createAuthenticatedHello(String message, String emailAddress) {
        Hello newHello = new Hello(message, emailAddress);
        helloRepository.persist(newHello);
    }


    public void createAnonymousHello(String message) {
        Hello newHello = new Hello(message);
        helloRepository.persist(newHello);
    }
}
