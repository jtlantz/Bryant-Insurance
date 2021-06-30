package org.muic.ssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class BryantInsuranceWebApp {

    public static void main(String[] args) {
        SpringApplication.run(BryantInsuranceWebApp.class, args); //run the server
    }
}

