package com.dongnesari.comm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Properties props = new Properties();

    static {
        // 클래스가 로드될 때 딱 한 번 프로퍼티 파일 로딩
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("properties/emailSender.properties")) {
            if (input == null) {
                throw new RuntimeException("emailSender.properties 파일을 찾을 수 없습니다.");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("emailSender.properties 파일 로딩 중 오류 발생", e);
        }
    }

    public static String getSenderEmail() {
        return props.getProperty("sender.email");
    }

    public static String getSenderPassword() {
        return props.getProperty("sender.password");
    }
}
