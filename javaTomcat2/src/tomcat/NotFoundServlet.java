package tomcat;

import standard.HttpServlet;
import standard.HttpServletRequest;
import standard.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class NotFoundServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        print404(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        print404(req, resp);
    }

    private void print404(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(404);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset='utf-8'>");
        writer.println("<title>没有该页面</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>404</h1>");
        writer.println("<p>没有找到该页面</p>");
        writer.println("<p>" + req.getPath() + "</p>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
