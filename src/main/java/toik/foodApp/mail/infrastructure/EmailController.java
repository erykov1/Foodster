package toik.foodApp.mail.infrastructure;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toik.foodApp.mail.domain.EmailService;

@RequestMapping("/api/mail")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class EmailController {

  EmailService emailService;

  @Autowired
  EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping("/sendSimpleMail")
  public void sendEmail(@RequestBody String to, String text, String subject) {
    emailService.sendSimpleMail(to, text, subject);
  }

  @PostMapping("/sendMailWithAttachment")
  public void sendEmailWithAttachment(@RequestBody String to, String text, String subject, String attachment) {
    emailService.sendMailWithAttachment(to, text, subject, attachment);
  }
}
