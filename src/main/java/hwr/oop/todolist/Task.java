package hwr.oop.todolist;


import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private Date date;
    private Status status = Status.INCOMPLETE;
    private String title;
    private static final long serialVersionUID = 8367141910137788612L;



    public String getTitle() {
        return this.title;
    }

    public void setTitle(@NotNull String title) throws NullPointerException {
        if (title.trim().equals("")) {
            throw new NullPointerException("The title can't be empty");
        } else this.title = title;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(@NotNull Status status) throws NullPointerException {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
