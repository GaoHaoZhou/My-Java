package webapps.ROOT;

import standard.Servlet;

import java.util.HashMap;
import java.util.Map;

/**
 <servlet>
     <servlet-name>Hello</servlet-name>
     <servlet-class>webapps.ROOT.HelloServlet</servlet-class>
 </servlet>

 <servlet>
    <servlet-name>Login</servlet-name>
    <servlet-class>webapps.ROOT.LoginServlet</servlet-class>
 </servlet>

 <servlet-mapping>
     <servlet-name>Login</servlet-name>
     <url-pattern>/login</url-pattern>
 </servlet-mapping>

 <servlet-mapping>
     <servlet-name>Hello</servlet-name>
     <url-pattern>/hello</url-pattern>
 </servlet-mapping>
 */
public class WebXML {
    // Key: url-pattern
    // Value: servlet-name
    private static Map<String, String> servletMapping = new HashMap<>();
    // Key: servlet-name
    // Value: servlet-class，简单实现成 Servlet 对象
    private static Map<String, Servlet> servlet = new HashMap<>();

    static  {
        // <servlet>
        servlet.put("Hello", new HelloServlet());
        servlet.put("Login", new LoginServlet());
        // <servlet-mapping>
        servletMapping.put("/login", "Login");
        servletMapping.put("/hello", "Hello");
    }

    /**
     * @param path
     * @return 路径对应的 Servlet 对象；null 表示没有对应的 Servlet 对象
     */
    public static Servlet map(String path) {
        String servletName = servletMapping.get(path);
        if (servletName == null) {
            return null;
        }

        return servlet.get(servletName);
    }
}
