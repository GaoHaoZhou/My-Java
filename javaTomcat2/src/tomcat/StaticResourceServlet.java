package tomcat;

import standard.HttpServlet;
import standard.HttpServletRequest;
import standard.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StaticResourceServlet extends HttpServlet {
    private static final String tomcatHome;

    static {
        // 从环境变量中，读取 HOME/BASE 目录
        tomcatHome = System.getenv("TOMCAT_HOME");
        System.out.println("TOMCAT_HOME: " + tomcatHome);
    }

    // URL: /hello.html
    // AbsPath: 动手实现Tomcat\webapp\hello.html
    // URL: /a/b/go.html
    // AbsPath: 动手实现Tomcat\webapp\a\b\go.html
    private static String getAbsolutePath(String path) {
        return tomcatHome + "\\webapp\\" + path;
    }

    public static boolean isMappingStaticResource(String path) {
        String filename = getAbsolutePath(path);
        File file = new File(filename);

        return file.exists() && file.isFile();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 读取静态资源对应的内容，并且把结果输出到响应中

        // 1. 找到 url 对应的静态资源文件
        String filename = getAbsolutePath(req.getPath());
        System.out.println("静态资源文件路径: " + filename);

        // 2. 根据静态资源文件的后缀名，确定 Content-Type 的类型
        String contentType = detectContentType(filename);
        //确定文件类型，弄了半天就是个这个
        resp.setContentType(contentType);


        // 3. 读取静态资源内容
        try (InputStream fileInputStream = new FileInputStream(filename)) {
            byte[] buffer = new byte[8192];
            int len;
            OutputStream responseOutputStream = resp.getOutputStream();

            while ((len = fileInputStream.read(buffer)) != -1) {
                // 把结果 buffer 内容写入 response
                responseOutputStream.write(buffer, 0, len);
            }

            responseOutputStream.flush();
        }
    }

    private static final Map<String, String> mimeTypeMap = new HashMap<>();
    private static final String DEFAULT_CONTENT_TYPE = "text/plain";
    static {
        mimeTypeMap.put("txt", "text/plain");
        mimeTypeMap.put("html", "text/html");
        mimeTypeMap.put("htm", "text/html");
        mimeTypeMap.put("jpg", "image/jpeg");
        mimeTypeMap.put("jpeg", "image/jpeg");
        mimeTypeMap.put("js", "application/javascript");
        // 等等
    }

    private String detectContentType(String filename) {
        // 事先有标准： MIME-Type 标准
        // 一组 Key-Value
        // 文件后缀 - Content-Type
        // .html - text/html
        // .txt - text/plain
        // .jpg - image/jpeg

        // 1. 从文件名中截取后缀
        int i = filename.lastIndexOf(".");
        if (i == -1) {
            return DEFAULT_CONTENT_TYPE;
        }
        String suffix = filename.substring(i + 1);
        System.out.println("静态资源文件后缀名: " + suffix);

        String contentType = mimeTypeMap.get(suffix);
        if (contentType == null) {
            return DEFAULT_CONTENT_TYPE;
        }

        return contentType;
    }
}
