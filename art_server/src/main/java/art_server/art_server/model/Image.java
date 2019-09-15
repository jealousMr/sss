package art_server.art_server.model;

public class Image {
    private String content;
    private String style;
    private String content_size;
    private String style_size;
    private String color;




    public String getColor() {
        return color;
    }

    public String getContent() {
        return content;
    }

    public String getContent_size() {
        return content_size;
    }

    public String getStyle() {
        return style;
    }

    public String getStyle_size() {
        return style_size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent_size(String content_size) {
        this.content_size = content_size;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setStyle_size(String style_size) {
        this.style_size = style_size;
    }

}
