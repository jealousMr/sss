package art_server.art_server.service;

import art_server.art_server.dao.ImageDao;
import art_server.art_server.util.Remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private Remote remote;
    @Autowired
    private MailService mailService;
    @Value("${web.upload-path}")
    private String localFileUrl;
    @Value("${img-path}")
    private  String localOutput;

    @Override
    public String change_default(String content_name, String style_name) {
        return imageDao.change_default(content_name,style_name);
    }

    @Override
    public String change_size(String content_name, String style_name, String content_size, String style_size) {
        return imageDao.change_size(content_name,style_name,content_size,style_size);
    }

    @Override
    public String change_style(String content_name, String style_name, String sytle_count) {
        return imageDao.change_style(content_name,style_name,sytle_count);
    }

    @Override
    public String change_color(String content_name, String style_name, String color) {
        return imageDao.change_color(content_name, style_name,color);
    }

    @Override
    public String change_size_style(String content_name, String style_name, String content_size, String style_size, String count) {
        return imageDao.change_size_style(content_name,style_name,content_size,style_size,count);
    }

    @Override
    public String change_size_color(String content_name, String style_name, String content_size, String style_size, String color) {
        return imageDao.change_size_color(content_name,style_name,content_size,style_size,color);
    }

    @Override
    public String change_style_color(String content_name, String style_name, String count, String color) {
        return imageDao.change_style_color(content_name,style_name,count,color);
    }

    @Override
    public String change_size_style_color(String content_name, String style_name, String content_size, String style_size, String color, String count) {
        return imageDao.change_size_style_color(content_name,style_name,content_size,style_size,color,count);
    }

    @Override
    public void async_change_size_style_color(String content_name,String style_name,String content_size,String style_size,String color,String count,String email) {
        String comm=imageDao.change_size_style_color(content_name,style_name,content_size,style_size,color,count);
        remote.remoteCall(comm);
        int end=content_name.lastIndexOf(".");
        String data=content_name.substring(0,end)+"_stylized_"+style_name;
        String img_url=localOutput+"/"+data;
        File file=new File(img_url);
        mailService.sendAttachementFileMail(email,
                "art图片风格艺术迁移", "感谢您使用art图片风格艺术迁移运用，请注意查收附件！", file);
    }



    @Override
    public void async_change_all_muilt_style(String content_name, String[] style_name, String content_size, String style_size, String color, String count[], String email) {
        String comm=imageDao.change_all_muilt_style(content_name,style_name,content_size,style_size,color,count);
        remote.remoteCall(comm);
        int end=content_name.lastIndexOf(".");
        String data=content_name.substring(0,end)+"_interpolation";
        String type=content_name.substring(end,content_name.length());
        data+=type;
        String img_url=localOutput+"/"+data;
        File file=new File(img_url);
        mailService.sendAttachementFileMail(email,
                "art图片风格艺术迁移", "感谢您使用art图片风格艺术迁移运用，请注意查收附件！", file);
    }

    @Override
    public void async_change_choice(String content_name, String style_id, String email) {
        String comm=imageDao.change_choice(content_name,style_id);
        remote.remoteCall(comm);
        String style_name="img"+style_id+".jpg";
        int end=content_name.lastIndexOf(".");
        String data=content_name.substring(0,end)+"_stylized_"+style_name;
        String img_url=localOutput+"/"+data;
        File file=new File(img_url);
        mailService.sendAttachementFileMail(email,
                "art图片风格艺术迁移", "感谢您使用art图片风格艺术迁移运用，请注意查收附件！", file);

    }
}
