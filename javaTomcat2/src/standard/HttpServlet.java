package standard;

import java.io.IOException;

public abstract class HttpServlet implements Servlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (req.getMethod()) {
            case "GET":
                doGet(req, resp);
                break;
            case "POST":
                doPost(req, resp);
                break;
            default:
                resp.setStatus(400);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // Method Not Allow
        resp.setStatus(405);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Method Not Allow
        resp.setStatus(405);
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
