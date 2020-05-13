package tomcat;

import standard.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServletResponseImpl implements HttpServletResponse {
    private static final Map<Integer, String> reasonMap = new HashMap<>();
    static {
        reasonMap.put(200, "OK");
        reasonMap.put(400, "Bad Request");
        reasonMap.put(404, "Not Found");
        reasonMap.put(500, "Internal Server Error");
    }

    private int status = 200;
    private Map<String, String> headersMap = new HashMap<>();
    private OutputStream outputStream;

    private ByteArrayOutputStream bodyOutputStream = new ByteArrayOutputStream(8192);
    private PrintWriter bodyPrintWriter;

    public HttpServletResponseImpl() throws UnsupportedEncodingException {
        bodyPrintWriter = new PrintWriter(new OutputStreamWriter(bodyOutputStream, "UTF-8"));
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void setHeader(String name, String value) {
        headersMap.put(name, value);
    }

    @Override
    public void setContentType(String contentType) {
        setHeader("Content-Type", contentType);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return bodyPrintWriter;
    }

    @Override
    public OutputStream getOutputStream() {
        return bodyOutputStream;
    }

    public static HttpServletResponseImpl build(OutputStream outputStream) throws UnsupportedEncodingException {
        HttpServletResponseImpl response = new HttpServletResponseImpl();

        // 基本的初始化功能
        response.outputStream = outputStream;

        return response;
    }

    public void send() throws IOException {
        // 1. 强制把所有 body 的内容都刷新到最终的目的 buffer 中
        bodyPrintWriter.flush();

        // 2. 计算 响应体的长度，填充 Content-Length 首部
        int contentLength = bodyOutputStream.size();
        // 3. 设置 Content-Length 首部
        setHeader("Content-Length", String.valueOf(contentLength));

        // 0. 为了方便写字符，所以使用 PrintWriter 封装了 socket.outputStream
        PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(outputStream, "UTF-8")
        );

        // 1. 先写入响应行
        sendResponseLine(printWriter);

        // 2. 再写入响应头
        sendResponseHeaders(printWriter);
        // 数据有可能还缓存在 printWriter(写入 Socket)
        // 为了确保 响应行 -> 响应头 -> 响应体的顺序
        // 刷新 PrintWriter，确保数据先写入 Socket outputStream 中
        printWriter.flush();

        // 3. 最后写响应体
        sendResponseBody(outputStream);
        // 确保数据最终写入 Socket 中
        outputStream.flush();
    }

    private void sendResponseBody(OutputStream outputStream) throws IOException {
        outputStream.write(bodyOutputStream.toByteArray());
    }

    private void sendResponseHeaders(PrintWriter printWriter) {
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            printWriter.print(key);
            printWriter.print(": ");
            printWriter.print(value);
            printWriter.print("\r\n");
        }

        printWriter.print("\r\n");
    }

    private void sendResponseLine(PrintWriter printWriter) {
        // <HTTP/1.0> 空格 <状态> 空格 <状态描述> CRLF
        printWriter.print("HTTP/1.0 ");
        printWriter.print(" ");
        printWriter.print(status);
        printWriter.print(" ");
        printWriter.print(reasonMap.get(status));
        printWriter.print("\r\n");
    }

    @Override
    public String toString() {
        return "HttpServletResponseImpl{" +
                "status=" + status +
                ", headersMap=" + headersMap +
                '}';
    }
}
