package standard;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface HttpServletResponse {
    void setStatus(int status);

    void setHeader(String name, String value);

    void setContentType(String contentType);

    PrintWriter getWriter() throws IOException;

    OutputStream getOutputStream() throws IOException;
}
