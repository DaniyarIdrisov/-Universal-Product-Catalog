package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender= new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getProperty("spring.mail.host"));
        javaMailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setUsername(environment.getProperty("spring.mail.username"));
        javaMailSender.setPassword(environment.getProperty("spring.mail.password"));

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
        props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

        return javaMailSender;
    }

}
