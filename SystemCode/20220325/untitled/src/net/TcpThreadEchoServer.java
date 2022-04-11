package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 9:27
 * Description: 多线程版本的TCP协议回显
 */
public class TcpThreadEchoServer {
    private ServerSocket serverSocket;

    public TcpThreadEchoServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动成功");
        while (true) {
            Socket clientSocket = serverSocket.accept();// 从内核态拉去连接，到用户态
            Thread t  = new Thread(() -> {
                processConnection(clientSocket);
            });
            t.start();
        }
    }

    public void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d] 客户端连接成功\n",clientSocket.getInetAddress().toString(), clientSocket.getPort());
        try (InputStream inputStream = clientSocket.getInputStream()) { // 请求的数据
            try (OutputStream outputStream = clientSocket.getOutputStream()) { // 响应的数据
                Scanner sc = new Scanner(inputStream);
                while (true) {
                    if (!sc.hasNext()) { // 输入流里面没数据了，跳出循环，断开连接
                        System.out.printf("[%s:%d] 客户端断开连接\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                        break;
                    }

                    // 读取客户端发送来的数据
                    String request = sc.next();
                    // 通过一个方法，根据请求来计算响应
                    String response = process(request);
                    // 将响应结果，返回到客户端
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(request);
                    writer.flush(); // 将结果，从缓冲区刷新到流里面去
                    System.out.printf("[%s:%d] req: %s; resp: %s\n", clientSocket.getInetAddress().toString(), clientSocket.getPort(),
                            request, response);

                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpThreadEchoServer server = new TcpThreadEchoServer(9090);
        server.start();
    }
}
