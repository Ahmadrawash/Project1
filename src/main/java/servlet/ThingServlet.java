package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ThingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ********************* added by me  *****************############################
        System.out.println("Beginning");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>header</h1></br>my response to ");
        pw.write(req.getParameter("test"));;
        pw.write(req.getParameter("thingy"));

        if (10 > 10) return;

        String queryString = req.getQueryString();
        String contextPath = req.getContextPath();
        String reqURI = req.getRequestURI();
        Enumeration<String> headerList =  req.getHeaderNames();
        while(headerList.hasMoreElements()) {
            String header = headerList.nextElement();
            System.out.println(header + ": " + req.getHeader(header));
        }

        String test = req.getParameter("test");
        String thingy = req.getParameter("thingy");
        resp.setStatus(200);
        resp.setContentType("plain/text");
        resp.getWriter().write("Thing Servlet: " + test + ", thingy: " + thingy);
        System.out.println("Thing Servlet: " + test + ", thingy: " + thingy);
        System.out.println(queryString + ", " + contextPath + ", " + reqURI);
    }
}
