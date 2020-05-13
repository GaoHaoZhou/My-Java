package standard;

import java.io.IOException;

public interface Servlet {
    void init();

    void service(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void destroy();
}
