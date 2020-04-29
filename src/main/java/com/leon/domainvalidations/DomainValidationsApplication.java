package com.leon.domainvalidations;

import com.leon.domainvalidations.user.User;
import com.leon.domainvalidations.domainvalidator.RequestType;
import com.leon.domainvalidations.domainvalidator.Status;
import com.leon.domainvalidations.domainvalidator.ValidationsResults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainValidationsApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DomainValidationsApplication.class, args);

        User user = new User("Leon", 19);
        ValidationsResults results = user.validateFor(RequestType.CREATE);

        if (results.getStatus().equals(Status.FAILED)) {
            System.out.println("Failed!");
        } else if (results.getStatus().equals(Status.PASSED)){
            System.out.println("Passed!");
        }
    }
}
