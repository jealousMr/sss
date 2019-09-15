package art_server.art_server.service;

import art_server.art_server.dao.MailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private MailDao mailDao;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        mailDao.sendSimpleMail(to,subject,content);
    }


    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        mailDao.sendInlineResourceMail(to,subject,content,rscPath,rscId);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        mailDao.sendHtmlMail(to,subject,content);
    }

    @Override
    public void sendAttachementFileMail(String sendto, String title, String content, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            messageHelper.setFrom(sender);
            messageHelper.setTo(sendto);
            messageHelper.setSubject(title);
            messageHelper.setText(content);

//            System.out.println("发件人：" + sender + "\n" +
//                    "收件人：" + sendto + "\n" +
//                    "标题：" + title + "\n" +
//                    "内容：" + content);

            //附件
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            String fileName = file.getName();
            System.out.println(fileName);
            messageHelper.addAttachment(fileName, fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);

    }


}
