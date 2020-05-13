package tomcat;

import standard.Servlet;
import webapps.ROOT.WebXML;

import java.net.Socket;
// HTTP 1.0 协议
// 一次事务中，读取一次请求，发送一次响应
// 1. 读取请求
// 2. 发送响应
// 3. 关闭连接
public class TransactionTask implements Runnable {
    private final Socket socket;
    private final NotFoundServlet notFoundServlet = new NotFoundServlet();
    private final StaticResourceServlet staticResourceServlet = new StaticResourceServlet();

    public TransactionTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // HTTP 1.0 协议，一条 TCP 连接中，只处理一组 HTTP 请求 - 响应
        try {
            // 1. 读取 TCP 连接中的输入
            // 2. 遵照 HTTP 请求的格式，解析输入
            // 3. 解析过程，可能读到错误格式的请求（直接不处理）
            // 4. 解析成功，封装成 HttpServletRequestImpl 对象
            HttpServletRequestImpl request = HttpServletRequestImpl.readAndParse(socket.getInputStream());
            // 为了观察中间步骤 —— DEBUG
            System.out.println(request);

            // 5. 构建一个空的 HttpServletResponseImpl 对象(进行了基本的初始化）
            HttpServletResponseImpl response = HttpServletResponseImpl.build(socket.getOutputStream());

            // 6. TODO: 区分是静态资源还是动态资源
            // 两种不同的策略
            // 第一种策略：优先选取静态资源
            // 首先去 静态资源 所在的目录，查找是否有 url 对应的文件
            // 从环境变量中读取 Tomcat 运行的 HOME/BASE 目录
            // 根据规则 (真实中：webapps/context path/... | webapp/...)
            // 第二种策略：优先选取动态资源
            Servlet servlet;
            if (request.getMethod().equals("GET") && StaticResourceServlet.isMappingStaticResource(request.getPath())) {
                // GET 并且 url 对应的静态资源存在
                servlet = staticResourceServlet;
            } else {
                // 7. 先只考虑动态资源 - 如何根据 URL 找到对应的 Servlet 类
                // Tomcat 中通过 web.xml 进行配置
                // 通过 WebXML 类，模拟该操作
                servlet = WebXML.map(request.getPath());
                // 平时我们 404 的原因
                // 1. Tomcat 找不到对应的 web.xml 文件 —— web应用/WEB-INF/web.xml
                // 2. Tomcat 在 web.xml 中没有找到 url 对应的 Servlet 类
                if (servlet == null) {
                    servlet = notFoundServlet;
                }
            }

            // 无论 servlet 如何对象是如何获取（静态Servlet/404Servlet/web应用）
            System.out.println(servlet.getClass().getCanonicalName());
            servlet.service(request, response); // 会根据不同的 method，调用不同的 doGet/doPost
            // response 就被应用正确填充了
            System.out.println(response);

            // 8. 按照 response 中的内容，组装成 HTTP 响应格式并发送
            response.send();

            socket.close();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
}
