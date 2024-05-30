package ru.sergeysemenov.request_logger.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.sergeysemenov.request_logger.config.LoggingInterceptor;

import java.util.logging.Logger;

public class RequestLoggerEnvPostProcessor implements EnvironmentPostProcessor {
    private final static Logger log = Logger.getLogger(LoggingInterceptor.class.getName());
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Вызов RequestLoggerEnvPostProcessor");
        String enabledPropertyValue = environment.getProperty("request-logger.active");

        if (enabledPropertyValue != null
                && !enabledPropertyValue.equalsIgnoreCase("true")
                && !enabledPropertyValue.equalsIgnoreCase("false")) {
            throw new RuntimeException("Ошибка во время проверки свойства 'endpoint.logging.active' в файле конфигурации. Допустимые значения: true или false.");
        }
    }
}
