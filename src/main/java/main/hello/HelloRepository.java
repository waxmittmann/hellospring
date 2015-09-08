package main.hello;

import main.persistence.SimpleRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "helloRepo")
public class HelloRepository extends SimpleRepository<Long, Hello> {
    public HelloRepository() {
        super(Hello.class);
    }
}
