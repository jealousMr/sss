package art_server.art_server.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ImageService {
    public String change_default(String content_name,String style_name);
    public String change_size(String content_name,String style_name,String content_size,String style_size);
    public String change_style(String content_name,String style_name,String sytle_count);
    public String change_color(String content_name,String style_name,String color);
    public String change_size_style(String content_name,
                                    String style_name,
                                    String content_size,
                                    String style_size,
                                    String count);
    public String change_size_color(String content_name,
                                    String style_name,String content_size,String style_size,String color);
    public String change_style_color(String content_name,String style_name,String count,String color);
    public String change_size_style_color(String content_name,String style_name,String content_size,String style_size,String color,String count);



    //异步处理
    //处理完毕后发送邮箱
    @Async
    public void async_change_size_style_color(String content_name,
                                              String style_name,
                                              String content_size,
                                              String style_size,
                                              String color,
                                              String count,
                                              String email);

    @Async
    public void async_change_all_muilt_style(String content_name,
                                             String style_name[],
                                             String content_size,
                                             String style_size,
                                             String color,
                                             String count[],
                                             String email);

    @Async
    public void async_change_choice(String content_name,
                                    String style_id,
                                    String email);
}
