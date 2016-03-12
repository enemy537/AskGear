package model;

/**
 * Created by Pedro on 10/03/2016.
 */
public class Curiosity {
    private String title;
    private String content;
    private String image;
    private String query;

    public Curiosity(String title, String content, String image, String query) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.query = query;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getQuery() {
        return query;
    }
}
