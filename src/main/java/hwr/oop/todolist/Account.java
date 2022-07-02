package hwr.oop.todolist;


import java.io.Serializable;


class Account implements Serializable {
    private static final long serialVersionUID = 782336846239423209L;
    private String password;
    private String name;



    Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    Account() {

    }


    void setPassword(String password) {
        if(!(password == "")) {
                this.password = password;
        } else {
            System.out.println("You need to write a password");
        }
    }


    String getPassword() {
        return password;
    }

    void setName(String name) {
        if (!(name == "")) {
                this.name = name;
        } else {
            System.out.println("You need to write a name");
        }
    }
    String getName() {
        return name;
    }
}
