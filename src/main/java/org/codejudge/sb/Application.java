package org.codejudge.sb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.codejudge.sb"})
public class Application {

    public static void main(String[] args) {
    	try {
        SpringApplication.run(Application.class, args);
    }catch(Exception e) {
    	e.printStackTrace();
    }
    }
}
