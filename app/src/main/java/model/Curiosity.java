package model;

/**
 * Created by Pedro on 10/03/2016.
 */
public class Curiosity {
    private String title;
    private String content;
    private String imageResource;

    public Curiosity(String title, String content, String imageResource) {
        super();
        this.title = title;
        this.content = content;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return content;
    }

    public String getImage() {
        return imageResource;
    }
}
