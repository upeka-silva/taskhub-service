package lk.project.taskhub.service.impl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.project.taskhub.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void settVerificationEmail(String s, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(s);
        helper.setSubject(subject);
        helper.setText(body,true);
        javaMailSender.send(message);

    }


}
