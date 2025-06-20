package com.dongnesari.comm.util;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    // 인증 코드 생성 (6자리 숫자)
    public static String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 100000~999999
        return String.valueOf(code);
    }

    public static void sendEmail(String verificationCode, String recipientEmail) throws AddressException {
        final String senderEmail = "disfute77@gmail.com"; // 보내는 사람 이메일
        final String senderPassword = "pmmfklslmhmmkeph"; // 앱 비밀번호 (2단계 인증 필요)

        String subject = "이메일 인증 코드";
        String body = "인증 코드는 다음과 같습니다:\n\n" + verificationCode;

        // SMTP 서버 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 세션 생성
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // 메시지 구성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail, "관리자"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // 전송
            Transport.send(message);
            System.out.println("이메일 전송 성공! 인증코드: " + verificationCode);
        } catch (AddressException e) {
            throw new AddressException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
