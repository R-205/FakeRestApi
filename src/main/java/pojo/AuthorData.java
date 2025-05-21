package pojo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AuthorData {
    private int id;
    private int idBook;
    private String firstName;
    private String lastName;


    public void setId(int id) {
        this.id = id;
    }
    public void setBookId(int idBook) {
        this.idBook = idBook;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }
    public int getIdBook() {
        return idBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}

