//package servlet;//package servlet;

//import DAOs.DataDAO;

//import DAOs.UsersDAO;

import DAOs.UsersDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import servlet.MyClass2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//import DAOs.*;

public class DataServlet extends HttpServlet {
    //UsersDataStore ds = new UsersDataStore();

    //dumb varaible: need to check how to get the last id (which is defiend as autoincrement)
     static int counter = 99;
     static FileLogger FLogger;


     public DataServlet()
     {
         //initiazlie logging file name
         System.out.println("Constructor of DataServlet");
         //initizlie File Logger
         String filePath=  "C:\\Users\\ahmed\\IdeaProjects\\ServletProject\\logs\\";
         File f = new File(filePath);
         try {
             FLogger = FileLogger.getFileLogger(filePath);
         }
         catch (Exception e) {
                 e.printStackTrace();
             }
         }

     //add (insert new) data
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoPost");
        //input JSON is as follows { "Name":"test", "Password":"123", "Email":"Email@Email.com"} id of user is autoincrement genreated automatically

        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = req.getInputStream();

        UsersDAO dao = mapper.readValue(req.getInputStream(), UsersDAO.class);
        //System.out.println("attempting to add user ID:"  + dao.getID() + ",name" + dao.getName() + ", password" + dao.getPassword() + ", email" + dao.getEmail());
        System.out.println("attempting to add user ID:"  + dao.getID().intValue() + ",name" + dao.getName() + ", password" + dao.getPassword() + ", email" + dao.getEmail());

        //user id is not used in the userDao class. it is genreated automtially when the record is inserted.
        //a dumb value of 9 is used to match the syntax of the UserDao constructro. and to return the value whenever a query about this user is executed
        UsersDAO u  = new UsersDAO(9, dao.getName(), dao.getPassword(), dao.getEmail()); //create pojo



        //PersistenceService.setData(u);
        resp.setStatus(202);



        /*
        System.out.println("current directory is: \n" + System.getProperty("user.dir"));
        System.out.println("full path is: \n"+ System.getProperty("user.dir") + "\\src/main/resources/jdbc.properties");
        File f= new File(System.getProperty("user.dir") + "\\src\\main\\resources\\jdbc.properties");
        if (f.exists()) System.out.println("f exist");
        else System.out.println("f doest exist");

        File f2= new File(System.getProperty("user.dir") + "\\src/main/resources/jdbc.properties");
        if (f2.exists()) System.out.println("f2 exist");
        else System.out.println("f2 doest exist");
        */

        //******** MyJDBC ************


            Repository<UsersDAO> rs = new Repository<UsersDAO>(new UsersDAO());
            rs.create(u);


            //%%%%%%%%%%%%%%%%%%%% add this line to where it is supposed to display all users *******
            //MyJDBC.DisplayData();
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% was commented out
            //MyJDBC.AddUser( u.getName(), u.getPassword(), u.getEmail()); // add user to db
            //confirmation message
            System.out.println("new user has been added to db and datastore. user info is as follows: " +
                    "    Id: " + u.getID() + " Name: " + u.getName() + ", password: " + u.getPassword() + ", email: " + u.getEmail());
            resp.getWriter().println("new user has been added to db and datastore. user info is as follows: " +
                    "    Id: " + u.getID() + " Name: " + u.getName() + ", password: " + u.getPassword() + ", email: " + u.getEmail());


            }
        catch (Exception exc)
        {
            System.out.println("Exception: " + exc.getMessage());
            exc.printStackTrace();
            LogToFile(exc);

        }

    }

    //update data
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        //input JSON is as follows { "Name":"test", "Password":"123", "Email":"Email@Email.com"} id of user is autoincrement genreated automatically

        System.out.println("DoPut");
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = req.getInputStream();


        UsersDAO dao = mapper.readValue(req.getInputStream(), UsersDAO.class);
        //System.out.println("attempting to update user ID:"  + dao.getID() + " ,name: " + dao.getName() + ", password: " + dao.getPassword() + ", email: " + dao.getEmail());
        System.out.println("attempting to update user ID:"  + dao.getID().intValue() + " ,name: " + dao.getName() + ", password: " + dao.getPassword() + ", email: " + dao.getEmail());

        //UsersDAO u  = new UsersDAO(counter++, dao.getName(), dao.getPassword(), dao.getEmail());
        //PersistenceService.setData(u);

        /*
        System.out.println("current directory is: \n" + System.getProperty("user.dir"));
        System.out.println("full path is: \n"+ System.getProperty("user.dir") + "\\src/main/resources/jdbc.properties");
        File f= new File(System.getProperty("user.dir") + "\\src\\main\\resources\\jdbc.properties");
        if (f.exists()) System.out.println("f exist");
        else System.out.println("f doest exist");

        File f2= new File(System.getProperty("user.dir") + "\\src/main/resources/jdbc.properties");
        if (f2.exists()) System.out.println("f2 exist");
        else System.out.println("f2 doest exist");
        */

        //******** MyJDBC ************
        try
        {
            //boolean result = MyJDBC.UpdateUser(dao.getID(), dao.getName(), dao.getPassword(), dao.getEmail());;

            Repository<UsersDAO> rs = new Repository<UsersDAO>(new UsersDAO());
            UsersDAO u = rs.update(dao);
            if (u != null) {
                //if (result){
                System.out.println("user has been udpated. user info is as follows: " +
                        "    Id" + dao.getID() + ", Name: " + dao.getName() + "password: " + dao.getPassword() + ", email: " + dao.getEmail());
                resp.getWriter().println("user has been udpated. user info is as follows: " +
                        "    Id" + dao.getID() + ", Name: " + dao.getName() + "password: " + dao.getPassword() + ", email: " + dao.getEmail());
                //}
            }
            else
            {
                System.out.println("couldn't udpate the  user. user info is as follows: " +
                        "    Id" + dao.getID() + ", Name: " + dao.getName() + "password: " + dao.getPassword() + ", email: " + dao.getEmail());
                resp.getWriter().println("couldn't udpated the  user. user info is as follows: " +
                        "    Id" + dao.getID() + ", Name: " + dao.getName() + "password: " + dao.getPassword() + ", email: " + dao.getEmail());
            }
            resp.setStatus(202);

        }
        catch (Exception exc)
        {
            System.out.println("Exception: " + exc.getMessage());
            exc.printStackTrace();
            LogToFile(exc);
        }

    }

    //delete data
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        System.out.println("DoDelete");
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = req.getInputStream();

        //DataDAO dao = mapper.readValue(req.getInputStream(), DataDAO.class);
        //MyClass<Integer> id = mapper.readValue(req.getInputStream(), MyClass.class);
        MyClass2<Integer> id = mapper.readValue(req.getInputStream(), MyClass2.class);
        //UsersDAO user = Services.PersistenceService.getData();

        //******** MyJDBC ************
        try {
            //display user with user id to console
            //JDBC2.Getuser(id.getValue());
            boolean result = MyJDBC.DeleteUser(id.getValue());
            if (result)
            resp.getWriter().println("The following user with id: " +  id.getValue() + " has been deleted from the database");
            else
                resp.getWriter().println("unsuccessfull attempt to delete The following user with id: " +  id.getValue() );
            resp.setStatus(202);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            LogToFile(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DoGet");
        try {
            Class.forName ("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = req.getInputStream();

        //******** MyJDBC ************
        try {
            //DataDAO dao = mapper.readValue(req.getInputStream(), DataDAO.class);
            MyClass2<Integer> id = mapper.readValue(req.getInputStream(), MyClass2.class);
            //UsersDAO user = Services.PersistenceService.getData();

            //******************** SHOULD RETURN A STRING/USER OBJECT ****************
            String userData = "";
            if (id.getValue() == 0) //get all users
              userData = MyJDBC.GetAllUsers();
            else
                userData = MyJDBC.GetOneUser("" + id.getValue()); // get user's id whose id is given

            //%%%%%%%%%%%%%%%% modify GetUesr to return user info &&&&&&&&&&&&&&
            resp.getWriter().println(userData);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            LogToFile(e);
        }
        resp.setStatus(202);

        //********* returning the JSON object ************************
        //DataCollection dataCollection = new DataCollection();
        //dataCollection.getDataCollection().add(GlobalStore.getObj());
        //dataCollection.getDataCollection().add(GlobalStore.getSuperObj());
        //ObjectMapper mapper = new ObjectMapper();
        //String json = mapper.writeValueAsString(dataCollection);
        //resp.setStatus(200);
        //resp.getWriter().write(json);
    }

    public void LogToFile(Exception e)
    {
        try {
            if (e != null)
                FLogger.log(e);
        }
        catch(Exception exc2)
        {
            System.out.println("Exception while logging: " + exc2.getMessage());
            exc2.printStackTrace();
        }
    }
}
