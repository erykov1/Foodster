package toik.foodApp.mail.domain;


public interface EmailService {

  public void sendSimpleMail(String to, String text, String subject);

  public void sendMailWithAttachment(String to, String text, String subject, String attachment);
}
