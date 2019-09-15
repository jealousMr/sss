package art_server.art_server;

import art_server.art_server.service.MailService;
import art_server.art_server.util.Command;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtServerApplicationTests {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private Command command;
    @Test
    public void contextLoads() {
        String rscPath = "C:/Users/91308/Desktop/cloud/ww.png";
        String rscId = "001";
        String content = "<html><body><h3><font color=\"red\">" + "大家好，这是springboot发送的HTML邮件，有图片哦" + "</font></h3>"
                + "<img src=\'cid:" + rscId + "\'></body></html>";
        mailService.sendInlineResourceMail("460513920@qq.com", "发送邮件测试", content, rscPath, rscId);
    }
    @Test
    public void cont(){
        Context context = new Context();
        context.setVariable("id", "001");
        context.setVariable("img_url","http://47.96.230.133/api/ 15663tu_stylized_15633hhs.jpg");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("460513920@qq.com", "这是一个模板文件", emailContent);
    }
    @Test
    public void dd(){
        File file=new File("C:/Users/91308/Desktop/cloud/img1.jpg");
        mailService.sendAttachementFileMail("460513920@qq.com",
                "art图片风格艺术迁移", "感谢您使用art图片风格艺术迁移运用，请注意查收附件！", file);
    }



}
