package com.gentlelc.employee.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Gentle_LC
 */
@EnableSwagger2
@SpringBootApplication
public class EmployeeManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManageApplication.class, args);
    }

}
