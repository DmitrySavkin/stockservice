package ru.savkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import ru.savkin.services.StockService;

@PropertySource(name = "myProperties", value = "values.properties")
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class Application implements ApplicationRunner, CommandLineRunner {


    @Autowired
    private StockService stockService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        stockService.process();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from Command runer");
    }
}
