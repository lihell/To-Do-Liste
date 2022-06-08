package hwr.oop.todolist;

//using Files for saving Accounts

import java.io.Serializable;
import java.util.Objects;

class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String password;
    private String name;



    Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    Account() {

    }


    public void setNewPassword(String password) throws NullPointerException{
        if (!Objects.equals(password, "null")) {
            this.password = password;
        } else {
            throw new NullPointerException("Please write a password");
        }
    }

    public String getNewPassword() {
        return password;
    }

    public void setNewName(String name) throws NullPointerException {
        if (!Objects.equals(name, "")) {
            this.name = name;
        } else {
            throw new NullPointerException("Please write a name");
        }
    }
    public String getNewName() {
        return name;
    }
}
