package tomcat;

import standard.HttpServletRequest;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Implement
public class HttpServletRequestImpl implements HttpServletRequest {
    private String method;
    private String path;
    private Map<String, String> parameterMap = new HashMap<>();
    private Map<String, String> headerMap = new HashMap<>();

    @Override
    public String getMethod() {
        return method;
    }

    /**
     * 不包括 QueryString 部分
     * @return
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * 读取 QueryString 部分
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        return parameterMap.get(name);
    }

    /**
     * 读取请求头
     * @param name
     * @return
     */
    @Override
    public String getHeader(String name) {
        return headerMap.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{\r\n");

        sb.append("    method='");
        sb.append(method);
        sb.append("'\r\n");

        sb.append("    path='");
        sb.append(path);
        sb.append("'\r\n");

        sb.append("    parameters={\r\n");
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            sb.append("        ");
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        sb.append("    }\r\n");

        sb.append("    headers={\r\n");
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            sb.append("        ");
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        sb.append("    }\r\n");
        sb.append("}\r\n");

        return sb.toString();
    }

    public static HttpServletRequestImpl readAndParse(InputStream inputStream) throws UnsupportedEncodingException {
        HttpServletRequestImpl request = new HttpServletRequestImpl();

        // request 属性的值，来自读取 inputStream 中的内容，并解析出来
        // HTTP 协议是文本协议，通过字符读取比较方便，InputStream 读取不太方便
        // HTTP 协议中大多使用 CRLF 进行分割，需要一个方便读取一行的类进行处理 —— Scanner
        Scanner scanner = new Scanner(inputStream, "UTF-8");

        // 1. 读取并解析 请求行（得到 method、path、parameterMap）
        readAndParseRequestLine(request, scanner);

        // 2. 读取并解析 请求头（得到 headerMap）
        readAndParseRequestHeaders(request, scanner);

        // 3. TODO: 解析请求体的部分

        // 返回完全初始化的 请求 对象
        return request;
    }

    private static void readAndParseRequestHeaders(HttpServletRequestImpl request, Scanner scanner) {
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                // 读到 CRLF 空行了
                // HTTP 协议表示，请求头结束了
                break;
            }

            // Key: Value
            String[] group = line.split(":");
            String key = group[0].trim();   // trim 去掉字符串两边的空白字符
            String value = group[1].trim();

            request.headerMap.put(key, value);
        }
    }

    private static void readAndParseRequestLine(HttpServletRequestImpl request, Scanner scanner) throws UnsupportedEncodingException {
        String line = scanner.nextLine();
        // line 里包括了 <方法> <URL> <版本>，中间以空格分割
        String[] group = line.split(" ");
        // TODO: 错误处理

        request.method = group[0];
        String url = group[1];
        // group[2] 是版本信息，不用

        // 解析 url 部分，分出 path 和 parameter 部分
        parseURL(request, url);
    }

    private static void parseURL(HttpServletRequestImpl request, String url) throws UnsupportedEncodingException {
        // 1. 不考虑完整的 URL（http://www.baidu.com/index.html)
        // 2. 只考虑相对的 URL（/index.html）
        // 3. 如何区分 path 部分 和 queryString 部分，使用 '?' 来进行分割

        // split 传入的是正则表达式，'?' 有特殊含义，需要转义
        String[] group = url.split("\\?");
        request.path = URLDecoder.decode(group[0], "UTF-8");

        // 考虑下没有 QueryString
        if (group.length == 2) {
            // 再解析 QueryString 部分
            // /index.html?name=peixinchen&password=123321
            String[] fragments = group[1].split("&");
            for (String fragment : fragments) {
                // name=peixinchen
                // password=123321
                // 切割出 name 和 value
                String[] kv = fragment.split("=");
                // TODO: 不考虑只有 key，没有 value 的情况；也不考虑 key 重复
                String key = URLDecoder.decode(kv[0], "UTF-8");
                String value = URLDecoder.decode(kv[1], "UTF-8");

                request.parameterMap.put(key, value);
            }
        }
    }
}
