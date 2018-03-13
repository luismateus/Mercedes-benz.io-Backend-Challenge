package presentation;

import domain.Root;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ApplicationController {
    public static void main(String[]args) {
        Root root = domain.Root.getInstance();
        root.set_bookings();
        root.set_dealers();
        SpringApplication.run(ApplicationController.class, args);
    }
}

