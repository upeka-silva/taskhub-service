package lk.project.taskhub.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


public interface EmailService {
   void settVerificationEmail(String s,String subject ,String body) throws MessagingException;


}
