package com.leon.domainvalidations;

import com.leon.domainvalidations.domainvalidator.RequestType;
import com.leon.domainvalidations.domainvalidator.ValidationsResults;
import com.leon.domainvalidations.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainValidationsApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DomainValidationsApplication.class, args);

        User user = new User("Leon", 17);
        ValidationsResults results = user.validateFor(RequestType.CREATE);

        if (results.isFailed()) {
            System.out.println(results.getFailureMessages());
        }
    }
}
