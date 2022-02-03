
public class MyTest {
    public MyTest()
    {

    }
    public void Adduser()
    {
        try
        {
            MyJDBC.DisplayData();
             MyJDBC.AddUser( "IntegratedTest", "11111", "IntegratedTest@revature.net");
             MyJDBC.Getuser(1);
             MyJDBC.Getuser(2);
             MyJDBC.DeleteUser(7);
             MyJDBC.UpdateUser(1, "NewName", "NewPassword123", "newEmail");
        }
        catch (Exception exc)
        {
            System.out.println("Exception: " + exc.getMessage());
            exc.printStackTrace();
        }
    }



}
