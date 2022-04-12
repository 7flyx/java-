package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-12
 * Time: 18:14
 * Description: 基于TCP协议 实现 回显 服务器端
 */
public class TcpEchoServer {
    private ServerSocket serverSocket; // 服务器端的socket

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动成功");
        ExecutorService service = Executors.newCachedThreadPool(); // 搞一个线程池
        while (true) {
            // 通过ServerSocket获取到Socket对象，没有客户端连接时，这里会进行阻塞等待
            Socket clientSocket = serverSocket.accept();
            // 通过线程池，处理这里的请求
            service.submit(() -> {
                processConnection(clientSocket);
            });
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d] 客户端连接成功\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        // TCP协议是面向字节流
        try (InputStream inputStream = clientSocket.getInputStream()) { // 获取到请求发送过来的数据
            Scanner sc = new Scanner(inputStream);
            try (OutputStream outputStream = clientSocket.getOutputStream()) { // 写入即将返回给客户端的数据
                // 写一个死循环，说明连接并没有断开，还会传输过来新的数据
                while (true) {
                    // 如果输入流中已经没有数据了，表示已经读取完了，退出循环即可
                    if (!sc.hasNext()) {
                        System.out.printf("[%s:%d] 客户端断开连接\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                        break;
                    }
                    String request = sc.next(); // 读取请求的内容
                    String response = process(request);
                    // 通过PrintWriter，将响应数据写入到输出流中
                    // 实则这里的PrintWriter，就是前面在写Servlet中，response参数中的getOutputStream
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(response);
                    // 这里的刷新，指的就是行刷新、全刷新和不刷新，这样的概念
                    writer.flush(); // 有可能数据并没有占满缓冲区，没有将其刷新进对象中，需要自己手动刷新一下
                    System.out.printf("[%s:%d] req: %s; resp: %s\n", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort(), request, response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}
