package art_server.art_server.controller;

import art_server.art_server.model.Image;
import art_server.art_server.service.ImageService;
import art_server.art_server.util.Command;
import art_server.art_server.util.Remote;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private Remote remote;
    @Value("${web.upload-path}")
    private String localFileUrl;

    @GetMapping("/changeImgSize")
    public String img_size(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("content_size")String content_size,@RequestParam("style_size")String style_size){
        String comm=imageService.change_size(image_name,style_name,content_size,style_size);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeStyle")
    public String img_style(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("count")String count){
        String comm=imageService.change_style(image_name,style_name,count);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeImgColor")
    public String img_color(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("color") String color){
        String comm=imageService.change_color(image_name,style_name,color);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeSizeAndStyle")
    public String img_size_style(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("content_size")String content_size,@RequestParam("style_size")String style_size,@RequestParam("count")String count){
        String comm=imageService.change_size_style(image_name,style_name,content_size,style_size,count);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeSizeAndcolor")
    public String img_size_color(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("content_size")String content_size,@RequestParam("style_size")String style_size,@RequestParam("color")String color){
        String comm=imageService.change_size_color(image_name,style_name,content_size,style_size,color);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeStyleAndColor")
    public String img_style_color(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("count")String count,@RequestParam("color")String color){
        String comm=imageService.change_style_color(image_name,style_name,count,color);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }
    @GetMapping("/changeSizeAndStyleAndColor")
    public String img_size_style_color(@RequestParam("image_name") String image_name, @RequestParam("style_name")String style_name,@RequestParam("content_size")String content_size,@RequestParam("style_size")String style_size,@RequestParam("color")String color,@RequestParam("count")String count){
        String comm=imageService.change_size_style_color(image_name,style_name,content_size,style_size,color,count);
        remote.remoteCall(comm);
        JSONObject js=new JSONObject();
        int end=image_name.lastIndexOf(".");
        String data=image_name.substring(0,end)+"_stylized_"+style_name;
        js.put("urls",data);
        return js.toJSONString();
    }


    @ResponseBody
    @PostMapping(path = "/changeAll")
    public String change_all(@RequestParam(value = "image",required = false) MultipartFile image, @RequestParam(value = "style",required = false)MultipartFile style, HttpServletRequest request) throws IOException {

        JSONObject jsons=new JSONObject();
        if(image==null||style==null){
            jsons.put("state","error");
            return jsons.toJSONString();
        }


        JSONObject json=new JSONObject();
        try {
            json=saveContentAndStyle(image,style);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (json.get("state").equals("error"))
            return json.toJSONString();
        //取参数
        String obj=request.getParameter("obj");
        JSONObject tmp= JSON.parseObject(obj);
        String content_size=tmp.getString("content_size");
        String style_size=tmp.getString("style_size");
        String color=tmp.getString("color");
        if(color.equals("false"))color="-1";
        String count=tmp.getString("count");
        String email=tmp.getString("email");
        String content_name=json.getString("image_name");
        String style_name=json.getString("style_name");
        //异步处理
        imageService.async_change_size_style_color(content_name,style_name,content_size,style_size,color,count,email);
        return "{\"state\":\"success\"}";
    }
    private JSONObject saveContentAndStyle(MultipartFile image, MultipartFile style) throws IOException {
        JSONObject json=new JSONObject();
        String imageName=image.getOriginalFilename();
        String styleName=style.getOriginalFilename();
        String imagePath=null,stylepath=null;
        String imageType=null,styleType=null;
        String imageEndName,styleEndName;

        imageType = imageName.indexOf(".") != -1 ? imageName.substring(imageName.lastIndexOf(".") + 1, imageName.length()) : null;
        styleType=styleName.indexOf(".") != -1 ? styleName.substring(styleName.lastIndexOf(".") + 1, styleName.length()) : null;
        if(imageType==null||styleType==null){
            json.put("state","error");
            return json;
        }
        if ("GIF".equals(imageType.toUpperCase())||"PNG".equals(imageType.toUpperCase())||"JPG".equals(imageType.toUpperCase())){
            Random random = new Random();
            String trueFileName = String.valueOf(System.currentTimeMillis()).substring(0,3)+random.nextInt(100) + imageName;
            imageEndName=trueFileName;
            imagePath=localFileUrl+"/content/"+trueFileName;
            image.transferTo(new File(imagePath));
        }else{
            json.put("state","error");
            return json;
        }
        if ("GIF".equals(styleType.toUpperCase())||"PNG".equals(styleType.toUpperCase())||"JPG".equals(styleType.toUpperCase())){
            Random random=new Random();
            String trueFileName = String.valueOf(System.currentTimeMillis()).substring(0,3)+random.nextInt(100) + styleName;
            styleEndName=trueFileName;
            stylepath=localFileUrl+"/style/"+trueFileName;
            style.transferTo(new File(stylepath));
        }else{
            json.put("state","error");
            return json;
        }
        json.put("image_name",imageEndName);
        json.put("style_name",styleEndName);
        json.put("state","success");
        return json;

    }





    @ResponseBody
    @PostMapping(path = "/change_muilt")
    public String change_muilt_styles(@RequestParam(value = "image",required = false) MultipartFile image,
                                      @RequestParam(value = "style1",required = false) MultipartFile style1,
                                      @RequestParam(value = "style2",required = false) MultipartFile style2,
                                      @RequestParam(value = "style3",required = false) MultipartFile style3,
                                      @RequestParam(value = "style4",required = false) MultipartFile style4,
                                      HttpServletRequest request) throws IOException {
        JSONObject json=save_muilt_img(image,style1,style2,style3,style4);
        //取参数
        String obj=request.getParameter("obj");
        JSONObject tmp= JSON.parseObject(obj);

        String content_size=tmp.getString("content_size");
        String style_size=content_size;
        String color=tmp.getString("color");
        if(color.equals("false"))color="-1";
        String count[]=new String[4];
        count[0]=tmp.getString("num1");
        count[1]=tmp.getString("num2");
        count[2]=tmp.getString("num3");
        count[3]=tmp.getString("num4");
        String email=tmp.getString("email");
        String content_name=json.getString("image_name");

        String style_name[]=new String[4];
        for (int i=0;i<style_name.length;i++)
            style_name[i]="-1";
        if(json.getString("style1")!=null)
            style_name[0]=json.getString("style1");
        if(json.getString("style2")!=null)
            style_name[1]=json.getString("style2");
        if(json.getString("style3")!=null)
            style_name[2]=json.getString("style3");
        if(json.getString("style4")!=null)
            style_name[3]=json.getString("style4");

        imageService.async_change_all_muilt_style(content_name,style_name,content_size,style_size,color,count,email);
        return "{\"state\":\"success\"}";
    }



    private JSONObject save_muilt_img(MultipartFile image,
                                      MultipartFile style1,
                                      MultipartFile style2,
                                      MultipartFile style3,
                                      MultipartFile style4)throws IOException{
        JSONObject result=new JSONObject();
        JSONObject temp;
        temp=save_one_img(image,1);
        if(temp.getString("state").equals("success"))
            result.put("image_name",temp.getString("image_name"));

        if(style1!=null){
            temp=save_one_img(style1,0);
            if(temp.getString("state").equals("success"))
                result.put("style1",temp.getString("image_name"));
        }

        if(style2!=null){
            temp=save_one_img(style2,0);
            if(temp.getString("state").equals("success"))
                result.put("style2",temp.getString("image_name"));
        }

        if(style3!=null){
            temp=save_one_img(style3,0);
            if(temp.getString("state").equals("success"))
                result.put("style3",temp.getString("image_name"));
        }

        if(style4!=null){
            temp=save_one_img(style4,0);
            if(temp.getString("state").equals("success"))
                result.put("style4",temp.getString("image_name"));
        }
        return result;

    }
    private JSONObject save_one_img(MultipartFile image,int argc)throws IOException {
        JSONObject json=new JSONObject();
        if(image==null){
            json.put("state","error");
            return json;
        }
        String imageName=image.getOriginalFilename();
        String imagePath=null;
        String imageType=null;
        String imageEndName;

        imageType = imageName.indexOf(".") != -1 ? imageName.substring(imageName.lastIndexOf(".") + 1, imageName.length()) : null;
        if(imageType==null){
            json.put("state","error");
            return json;
        }
        if ("GIF".equals(imageType.toUpperCase())||"PNG".equals(imageType.toUpperCase())||"JPG".equals(imageType.toUpperCase())){
            Random random = new Random();
            String trueFileName = String.valueOf(System.currentTimeMillis()).substring(0,3)+random.nextInt(100) + imageName;
            imageEndName=trueFileName;
            if (argc==1)
              imagePath=localFileUrl+"/content/"+trueFileName;
            else
                imagePath=localFileUrl+"/style/"+trueFileName;
            image.transferTo(new File(imagePath));
        }else{
            json.put("state","error");
            return json;
        }
        json.put("image_name",imageEndName);
        json.put("state","success");
        return json;
    }

    @ResponseBody
    @PostMapping(path = "/choice_style")
    public String change_choice_style(@RequestParam(value = "image",required = false) MultipartFile image,HttpServletRequest request) throws IOException {
            JSONObject json=save_one_img(image,1);
            String content_name=json.getString("image_name");
        String obj=request.getParameter("obj");
        JSONObject tmp= JSON.parseObject(obj);

        String email=tmp.getString("email");
        String style_id=tmp.getString("img_id");
        imageService.async_change_choice(content_name,style_id,email);
        return "{\"state\":\"success\"}";
    }


}
