package standard;

public interface HttpServletRequest {
    String getMethod();

    String getPath();

    String getParameter(String name);

    String getHeader(String name);
}
