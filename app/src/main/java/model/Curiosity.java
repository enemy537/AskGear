package model;

/**
 * Created by Pedro on 10/03/2016.
 * This class models objects received from JSON dataset.
 */
public class Curiosity {
    private String title;
    private String content;
    private String image;
    private String query;

    /**
     * Constructor
     * @param title Curiosity's title
     * @param content Curiosity's text with description about subject
     * @param image Curiosity's descritive image
     * @param query Curiosity's string to use in Google search intent
     */
    public Curiosity(String title, String content, String image, String query) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.query = query;
    }

    /**
     * @return Curiosity's title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @return Curiosity's content text
     */
    public String getText() {
        return content;
    }
    /**
     * @return Curiosity's descritive image
     */
    public String getImage() {
        return image;
    }
    /**
     * @return Curiosity's Google search query
     */
    public String getQuery() {
        return query;
    }
}
