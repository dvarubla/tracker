package dvarubla.tracker;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Locale en = new Locale("en", "US");
        Locale.setDefault(en);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Module datatypeHibernateModule() {
        Hibernate5Module module = new Hibernate5Module();
        module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        return module;
    }
}
