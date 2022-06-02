package hwr.oop.todolist;


import org.jetbrains.annotations.NotNull;

import java.util.Date;

class Task {
    private Date date;
    private String status;
    private String title;



    public String getTitle() {
        return this.title;
    }

    public void setTitle(@NotNull String title) throws NullPointerException {
        if (title.trim().equals("")) {
            throw new NullPointerException("The title can't be empty");
        } else this.title = title;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(@NotNull String status) throws NullPointerException {
        if (status.trim().equals("")) {
            throw new NullPointerException("The Status of the Task has to be set.");
        } else this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
