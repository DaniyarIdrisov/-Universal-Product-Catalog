package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.utils;

public interface EmailUtil {

    void sendMail(String to, String subject, String from, String text);

}

