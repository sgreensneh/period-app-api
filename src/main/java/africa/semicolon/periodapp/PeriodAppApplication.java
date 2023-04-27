package africa.semicolon.periodapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PeriodCare API",
                version = "v1",
                description = "This app provides REST APIs for PeriodCare",
                contact = @Contact(
                        name = "PeriodCare Support",
                        email = "r.adebayo@native.semicolon.africa"
                )
        )
)
@Slf4j
@ConfigurationPropertiesScan
@EnableAsync
public class PeriodAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeriodAppApplication.class, args);
        log.info("::::::: Server running :::::::");
    }

}
