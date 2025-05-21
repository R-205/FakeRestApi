package pojo;

public class BookData {
    private int id;
    private String title;
    private String description = "string";
    private int pageCount = 0;
    private String excerpt = "string";
    private String publishDate = "2025-04-27T14:31:56.299Z";

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
