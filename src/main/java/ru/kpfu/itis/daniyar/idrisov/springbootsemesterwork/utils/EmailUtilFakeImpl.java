package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class EmailUtilFakeImpl implements EmailUtil {

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println(text);
    }

}
