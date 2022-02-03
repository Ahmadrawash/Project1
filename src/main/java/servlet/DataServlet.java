package servlet;

import DAOs.DataDAO;
import DAOs.UsersDAO;
import Services.PersistenceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


public class DataServlet extends HttpServlet {
    static int counter = 1000;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = req.getInputStream();

        //System.out.println("testing: " + is.toString());

        DataDAO dao = mapper.readValue(req.getInputStream(), DataDAO.class);
        System.out.println("dao: " + dao.getString() + ", " + dao.getInteger() + ", " + dao.isBool());

        //input JSON is as follows { "Name":"test", "Password":"123", "Email":"Email@Email.com"}
        UsersDAO u  = new UsersDAO(counter++, dao.getString(), dao.getString(), dao.getString());
        //CustomArrayList<Integer> ar = new CustomArrayList<Integer>();

        //MYORM.Adduser();
        //MyJDBC.AddUser( "random user", "99999", "random Users@revature.net");

        //System.out.println("\"random user\" has been added");

        /*
        //send POST with body containing string, integer and bool

        System.out.println("Query: "+ req.getQueryString());
        System.out.println(req.getRequestURL());
        System.out.println(req.getContextPath());
        System.out.println(
                "String:  [" + req.getParameter("string") + "], " +
                "Integer: [" + req.getParameter("integer") + "], " +
                        "bool: [" + req.getParameter("bool") + "]");
        String s = req.getParameter("string");
        Integer i = Integer.parseInt(req.getParameter("integer"));
        boolean b = Boolean.parseBoolean(req.getParameter("bool"));

        DataDAO data = new DataDAO(s, i, b);

        PersistenceService.setData(data);

        resp.setStatus(202);
        resp.getWriter().print("Data accepted.");
        */

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataDAO data = PersistenceService.getData();

        String s = data.getString();
        Integer i = data.getInteger();
        boolean b = data.isBool();

        resp.setStatus(202);
        resp.getWriter().print("Data: " + s + ", " + i.toString() + ", " + b);
    }
}
