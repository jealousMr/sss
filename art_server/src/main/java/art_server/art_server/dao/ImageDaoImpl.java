package art_server.art_server.dao;

import art_server.art_server.util.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao {
    @Autowired
    private Command command;
    @Override
    public String change_default(String content_name, String style_name) {
        return command.change_default(content_name,style_name);
    }

    @Override
    public String change_size(String content_name, String style_name, String content_size, String style_size) {
        return command.change_size(content_name,style_name,content_size,style_size);
    }

    @Override
    public String change_style(String content_name, String style_name, String sytle_count) {
        return command.change_style(content_name,style_name,sytle_count);
    }

    @Override
    public String change_color(String content_name, String style_name, String color) {
        return command.change_color(content_name, style_name,color);
    }

    @Override
    public String change_size_style(String content_name, String style_name, String content_size, String style_size, String count) {
        return command.change_size_style(content_name,style_name,content_size,style_size,count);
    }

    @Override
    public String change_size_color(String content_name, String style_name, String content_size, String style_size, String color) {
        return command.change_size_color(content_name,style_name,content_size,style_size,color);
    }

    @Override
    public String change_style_color(String content_name, String style_name, String count, String color) {
        return command.change_style_color(content_name,style_name,count,color);
    }

    @Override
    public String change_size_style_color(String content_name, String style_name, String content_size, String style_size, String color, String count) {
        return command.change_size_style_color(content_name,style_name,content_size,style_size,color,count);
    }

    @Override
    public String change_all_muilt_style(String content_name, String[] style_names, String content_size, String style_size, String color, String count[]) {
        return command.change_all_muilt_style(content_name,style_names,content_size,style_size,color,count);
    }

    @Override
    public String change_choice(String content_name, String style_id) {
        return command.change_choice(content_name,style_id);
    }
}
