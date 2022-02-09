//package servlet;//package servlet;

//mport DAOs.UsersDAO;

import DAOs.UsersDAO;
import Services.PersistenceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JacksonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("json");
        ObjectMapper mapper = new ObjectMapper();
        //DataDAO data = mapper.readValue(json, DataDAO.class);
        //Services.PersistenceService.setData(data);
        UsersDAO data = mapper.readValue(json, UsersDAO.class);
        PersistenceService.setData(data);

        resp.setStatus(202);
        resp.getWriter().print("JSON accepted.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        //DataDAO data = Services.PersistenceService.getData();
        UsersDAO data = PersistenceService.getData();
        String json = mapper.writeValueAsString(data);

        resp.setStatus(202);
        resp.getWriter().print(json);
    }
}
