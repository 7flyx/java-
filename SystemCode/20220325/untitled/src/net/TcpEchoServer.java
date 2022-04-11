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
 * Time: 8:25
 * Description: 基于TCP协议的回显 服务器端,ServerSocket 和Socket
 */
public class TcpEchoServer {
    private ServerSocket serverSocket;

    public TcpEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动成功");
//        此处这么写，服务器只能同时接收来自一个客户端的数据，因为processConnection方法，里面也是死循环
        // 比如等processConnection方法出来之后，才能获取到下一个客户端数据的内容
        // 使用多线程 的方式，能够解决这样一个问题
        while (true) {
            // 通过ServerSocket对象，接收到Socket对象，通过socket对象来进行操作-会阻塞等待
            Socket clientSocket = serverSocket.accept();
            processConnection(clientSocket); // 和客户端进行通信，通过这个方法来处理整个的连接过程
        }

    }

    private void processConnection(Socket clientSocket) {
        // 首先打印日志，显示与客户端建立连接
        System.out.printf("[%s:%d] 客户端建立连接！\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
        // 以下进行通信
        // 通过socket对象取出 输入流 对象，读这个输入流，就相当于从 网卡 读取数据
        // 通过socket对象取出 输出流 对象，写这个输出流，就相当于向 网卡 写数据
        try (InputStream inputStream = clientSocket.getInputStream()) { // TCP是面向字节流，这里获取到一个输入流来读取传输过来的信息
            try (OutputStream outputStream = clientSocket.getOutputStream()) { // 获取到一个输出流，用于向客户端返回数据
                Scanner sc = new Scanner(inputStream);
                while (true) {
                    if (!sc.hasNext()) { // 如果输入流已经没有数据了，说明可以结束本次通信了
                        System.out.printf("[%s:%d] 客户端断开连接!\n", clientSocket.getInetAddress().toString(), clientSocket.getPort());
                        break;
                    }

                    String request = sc.next();
                    // 根据请求数据，计算出响应数据
                    String response = process(request);
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(response);
                    writer.flush();
                    System.out.printf("[%s:%d] req: %s; resp: %s\n", clientSocket.getInetAddress().toString(),
                            clientSocket.getPort(), request, response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TcpEchoServer server = new TcpEchoServer(9090);
        server.start();
    }
}
