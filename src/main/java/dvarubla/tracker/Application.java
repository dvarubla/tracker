package dvarubla.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Locale en = new Locale("en", "US");
        Locale.setDefault(en);
        SpringApplication.run(Application.class, args);
    }
}
