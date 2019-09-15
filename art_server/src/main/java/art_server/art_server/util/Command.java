package art_server.art_server.util;

import org.springframework.stereotype.Component;

@Component
public class Command {


    public String change_default(String content_name,String style_name){
        String comm="CUDA_VISIBLE_DEVICES= python test.py --content input/content/"+content_name+" --style input/style/"+style_name+" --content_size 156 --style_size 156 --crop";
        return comm;
    }

    public String change_size(String content_name,String style_name,String content_size,String style_size){
        if(content_size.equals("0")||content_size==null)
            content_size="512";
        if(style_size.equals("0")||style_size==null)
            style_size="512";
        String comm= "CUDA_VISIBLE_DEVICES= python test.py --content input/content/"+content_name+" --style input/style/"+style_name+"  --content_size "+content_size+" --style_size "+style_size+" --crop";
        return comm;
    }
    public String change_style(String content_name,String style_name,String sytle_count){
        String comm="CUDA_VISIBLE_DEVICES= python test.py --content input/content/"+content_name+" --style input/style/"+style_name+"  --alpha "+sytle_count;
        return comm;
    }
    public String change_color(String content_name,String style_name,String color){
        String comm="";
        if(color.equals("-1")||color.equals("")||color.equals(" ")||color==null||color.length()==0)
            comm=change_default(content_name,style_name);
        else
            comm=change_default(content_name,style_name)+" --preserve_color";
        return comm;
    }
    public String change_size_style(String content_name,String style_name,String content_size,String style_size,String count){
        String comm=change_size(content_name,style_name,content_size,style_size)+" --alpha "+count;
        return comm;
    }
    public String change_size_color(String content_name,String style_name,String content_size,String style_size,String color){
        String comm;
        if(color.equals("-1")||color.equals("")||color.equals(" ")||color==null||color.length()==0){
            comm=change_size(content_name,style_name,content_size,style_size);
        }else
            comm=change_size(content_name,style_name,content_size,style_size)+" --preserve_color";
        return comm;
    }
    public String change_style_color(String content_name,String style_name,String count,String color){
        String comm=change_style(content_name,style_name,count);
        if(color.equals("-1")||color.equals("")||color.equals(" ")||color==null||color.length()==0)
            return comm;
        else
            return comm+" --preserve_color";
    }
    public String change_size_style_color(String content_name,String style_name,String content_size,String style_size,String color,String count){
        String comm="";
        if(color.equals("-1")||color.equals("")||color.equals(" ")||color==null||color.length()==0)
            comm=change_size_style(content_name,style_name,content_size,style_size,count);
        else
            comm=change_size_style(content_name,style_name,content_size,style_size,count)+" --preserve_color";
        return comm;
    }

    public String change_all_muilt_style(String content_name,String style_names[],String content_size,String style_size,String color,String count[]){
        String comm="python test.py --content input/content/"+content_name+" --style ";
        int style_count=0;
        if(!style_names[0].equals("-1")){
            comm+="input/style/"+style_names[0];
            style_count++;
        }
        if(!style_names[1].equals("-1")){
            if(style_names[0].equals("-1"))
                comm+="input/style/"+style_names[1];
            else
            comm+=",input/style/"+style_names[1];
            style_count++;
        }
        if(!style_names[2].equals("-1")){
            if(style_names[0].equals("-1")&&style_names[1].equals("-1"))
                comm+="input/style/"+style_names[2];
            else
                comm+=",input/style/"+style_names[2];
            style_count++;
        }
        if(!style_names[3].equals("-1")){
            if(style_names[0].equals("-1")&&style_names[1].equals("-1")&&style_names[2].equals("-1"))
                comm+="input/style/"+style_names[3];
            else
                comm+=",input/style/"+style_names[3];
            style_count++;
        }
        switch (style_count){
            case 1:{
                comm+=" --style_interpolation_weights "+count[0];
                break;
            }
            case 2:{
                comm+=" --style_interpolation_weights "+count[0]+","+count[1];
                break;
            }
            case 3:{
                comm+=" --style_interpolation_weights "+count[0]+","+count[1]+","+count[2];
                break;
            }
            case 4:{
                comm+=" --style_interpolation_weights "+count[0]+","+count[1]+","+count[2]+","+count[3];
                break;
            }
        }
        if (!color.equals("-1"))
            comm+=" --preserve_color";
        comm+=" --content_size "+content_size;
        comm+=" --style_size "+style_size+" --crop";
        return comm;
    }

    public String change_choice(String content_name,String style_id){
        String comm="CUDA_VISIBLE_DEVICES= python test.py --content input/content/"+content_name+" --style input/style/choice/img"+style_id+".jpg";
        return comm;
    }
}
