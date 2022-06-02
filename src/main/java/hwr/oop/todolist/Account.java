package hwr.oop.todolist;

//using Files for saving Accounts

class Account {
    private String password;
    private String name;



    Account(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public void setNewPassword() {
        if (password != null) {
            this.password = password;
        } else {
            System.out.println("Please write a password!!");
        }
    }

    public String getNewPassword() {
        return password;
    }

    public void setNewName() {
        if (name != null) {
            this.name = name;
        } else {
            System.out.println("Please write a name!!");
        }
    }
    public String getNewName() {
        return name;
    }

    public Account getAccount() {
        return new Account(this.name, this.password);
    }

    /*public ToDoList saveToDoListToAccount(ToDoList todo) {
        return ;
    }

    public onlyOneListPerAccount() {

    }*/

}
