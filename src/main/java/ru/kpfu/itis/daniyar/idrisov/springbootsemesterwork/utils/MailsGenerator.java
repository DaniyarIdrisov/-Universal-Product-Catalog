package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.utils;

public interface MailsGenerator {

    String getMailForConfirm(String serverUrl, String code);

}
