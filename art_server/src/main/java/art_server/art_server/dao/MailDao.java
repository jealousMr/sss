package art_server.art_server.dao;

public interface MailDao {
    void sendSimpleMail(String to, String subject, String content);
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    void sendHtmlMail(String to, String subject, String content);

}
