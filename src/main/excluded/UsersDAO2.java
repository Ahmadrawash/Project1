//package DAOs;

public class UsersDAO2 {
    private int ID;
    private String Name;
    private String Password;
    private String Email;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UsersDAO2()
    {

    }

    public UsersDAO2(int id, String Name, String Password, String Email)
    {
        this.setID(id);
        this.setName(Name);
        this.setPassword(Password);
        this.setEmail(Email);
        //SaveUser();
    }

    public boolean SaveUser()
    {

        return true;
    }


}
