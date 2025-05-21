package pojo;

public class CoverPhotoData {
    private int id;
    private int idBook;
    private String url = "string";

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public void setURL(String url){
        this.url = url;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getUrl() {
        return url;
    }
}
