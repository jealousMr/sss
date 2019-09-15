package art_server.art_server.controller;

import art_server.art_server.service.ImageService;
import art_server.art_server.service.MailService;
import art_server.art_server.util.Remote;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@RestController
public class TeeController {

    @Value("${web.upload-path}")
    private String localFileUrl;

    @Autowired
    private Remote remote;
    @Autowired
    private ImageService imageService;

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/hell")
    public String hh(String kk){
        System.out.println(kk);
        return "eeeee";
    }

    @ResponseBody
    @PostMapping(path = "/img")
    public String getImg(@RequestParam(value = "image",required = false)MultipartFile image,@RequestParam(value = "style",required = false)MultipartFile style,HttpServletRequest request) throws IOException {

        JSONObject json=new JSONObject();
        if(image==null||style==null){
            json.put("state","error");
            return json.toJSONString();
        }
        String imageName=image.getOriginalFilename();
        String styleName=style.getOriginalFilename();
        String imagePath=null,stylepath=null;
        String imageType=null,styleType=null;

        String imageEndName,styleEndName;

        imageType = imageName.indexOf(".") != -1 ? imageName.substring(imageName.lastIndexOf(".") + 1, imageName.length()) : null;
        styleType=styleName.indexOf(".") != -1 ? styleName.substring(styleName.lastIndexOf(".") + 1, styleName.length()) : null;
        if(imageType==null||styleType==null){
            json.put("state","error");
            return json.toJSONString();
        }
        if ("GIF".equals(imageType.toUpperCase())||"PNG".equals(imageType.toUpperCase())||"JPG".equals(imageType.toUpperCase())){
            Random random = new Random();
            String trueFileName = String.valueOf(System.currentTimeMillis()).substring(0,3)+random.nextInt(100) + imageName;
            imageEndName=trueFileName;
            imagePath=localFileUrl+"/content/"+trueFileName;
            image.transferTo(new File(imagePath));
        }else{
            json.put("state","error");
            return json.toJSONString();
        }
        if ("GIF".equals(styleType.toUpperCase())||"PNG".equals(styleType.toUpperCase())||"JPG".equals(styleType.toUpperCase())){
            Random random=new Random();
            String trueFileName = String.valueOf(System.currentTimeMillis()).substring(0,3)+random.nextInt(100) + styleName;
            styleEndName=trueFileName;
            stylepath=localFileUrl+"/style/"+trueFileName;
            style.transferTo(new File(stylepath));
        }else{
            json.put("state","error");
            return json.toJSONString();
        }
        json.put("image_name",imageEndName);
        json.put("style_name",styleEndName);
        json.put("state","success");
        return json.toJSONString();
    }

    @GetMapping("/changeImg")
    public String changeImg(@RequestParam("image_name") String image_name,@RequestParam("style_name")String style_name){
        String comm=imageService.change_default(image_name,style_name);
        String state= (String) remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }









}
