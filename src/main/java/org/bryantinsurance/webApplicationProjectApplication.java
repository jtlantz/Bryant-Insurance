package org.bryantinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class webApplicationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(webApplicationProjectApplication.class, args);
    }

}
