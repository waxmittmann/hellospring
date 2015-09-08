package main;

import main.security.AuthenticationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

//This gets rid of errors when @Autowiring; apparently without this, spring attempts to inject spring proxies into
//autowired fields, but the repository (for some reason) needs a different (CGLIB, I think)? proxy
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        System.out.println(
            ".................................\n" +
            "...Hello World from..............\n" +
            ".............the land of spring..\n" +
            ".................................\n"
        );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/hellos/**");
    }
}

