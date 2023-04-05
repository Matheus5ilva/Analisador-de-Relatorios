package br.com.analisador.core.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ValidationConfig.class);

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        logger.info("Configuring validation bean...");
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
