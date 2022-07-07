package hwr.oop.todolist;


import java.io.Serializable;


public class Account implements Serializable {
    private static final long serialVersionUID = 782336846239423209L;
    private String password;
    private String name;



    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {

    }


    public void setPassword(String password) {
        if(!(password == "" || password == null)) {
                this.password = password;
        } else {
            System.out.println("You need to write a password");
        }
    }


    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        if (!(name == "" || name == null)) {
                this.name = name;
        } else {
            System.out.println("You need to write a name");
        }
    }
    public String getName() {
        return name;
    }
}
