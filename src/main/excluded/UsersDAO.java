//package DAOs;//package DAOs;

import Annotations.*;

@Entity(tableName = "UsersAccounts")
public class UsersDAO {
    @PrimaryKey(autoIncrement = true)
    @Property(fieldName = "id")
    private int ID;
    @Property(fieldName = "name")
    private String Name;
    @Property(fieldName = "password")
    private String Password;
    @Property(fieldName = "email")
    private String Email;

    @Getter(fieldName = "id")
    public int getID() {
        return ID;
    }

    @Setter(fieldName = "id")
    public void setID(int ID) {
        this.ID = ID;
    }

    @Getter(fieldName = "name")
    public String getName() {
        return Name;
    }

    @Setter(fieldName = "name")
    public void setName(String name) {
        Name = name;
    }

    @Getter(fieldName = "password")
    public String getPassword() {
        return Password;
    }

    @Setter(fieldName = "password")
    public void setPassword(String password) {
        Password = password;
    }

    @Getter(fieldName = "email")
    public String getEmail() {
        return Email;
    }

    @Setter(fieldName = "email")
    public void setEmail(String email) {
        Email = email;
    }

    public UsersDAO()
    {

    }

    public UsersDAO(int id, String Name, String Password, String Email)
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
