package org.spring.camel.example;

import io.hawt.springboot.EnableHawtio;
import io.hawt.springboot.HawtioConfiguration;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import static io.hawt.web.AuthenticationFilter.HAWTIO_AUTHENTICATION_ENABLED;

@SpringBootApplication
@EnableHawtio
public class ApplicationInitializer extends SpringBootServletInitializer {

    private static String HAWTIO_AUTHENTICATION_ENABLED_VALUE = "false";

    public static void main(final String[] arguments) {
        final Object[] configurationList = new Object[] {
                ApplicationInitializer.class,
                CamelAutoConfiguration.class,
                HawtioConfiguration.class
        };

        System.setProperty(HAWTIO_AUTHENTICATION_ENABLED, HAWTIO_AUTHENTICATION_ENABLED_VALUE);

        SpringApplication.run(configurationList, arguments);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder springApplicationBuilder) {
        return springApplicationBuilder.sources(ApplicationInitializer.class);
    }

}
