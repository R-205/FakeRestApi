package pojo;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityData {
    private int id;
    private String title;
    private boolean completed = true;
    private String dueDate = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void dueDate(String dueDate) {
        this.dueDate = dueDate;
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public String getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }


}
