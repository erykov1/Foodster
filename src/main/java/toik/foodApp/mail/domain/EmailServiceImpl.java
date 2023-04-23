package toik.foodApp.mail.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;


@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
class EmailServiceImpl implements EmailService {

  @Autowired
  JavaMailSender javaMailSender;

  static Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

  @Async
  @Override
  public void sendSimpleMail(String to, String text, String subject) {
    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom("<gmail>");
      mailMessage.setTo(to);
      mailMessage.setText(text);
      mailMessage.setSubject(subject);

      javaMailSender.send(mailMessage);
      LOGGER.info("Simple mail has been sent");
    } catch (Exception e) {
      LOGGER.error("failed to send simple email");
      e.printStackTrace();
    }
  }

  @Async
  @Override
  public void sendMailWithAttachment(String to, String text, String subject, String attachment) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
      mimeMessageHelper.setFrom("<gmail>");
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setText(text);
      mimeMessageHelper.setSubject(subject);

      FileSystemResource file = new FileSystemResource(attachment);
      mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

      javaMailSender.send(mimeMessage);
      LOGGER.info("Mail with attachment has been sent");
    } catch (MessagingException e) {
      LOGGER.error("failed to send mail with attachment");
      e.printStackTrace();
    }
  }
}
