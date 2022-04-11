package net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-11
 * Time: 8:49
 * Description: 基于TCP协议 实现回显 的客户端
 */
public class TcpEchoClient {
    // 服务器的IP和端口号
    private String serverIp;
    private int port;
    private Socket socket;

    public TcpEchoClient(String ip, int port) throws IOException {
        this.serverIp = ip;
        this.port = port;
        this.socket = new Socket(serverIp, port);
    }

    public void start() {
        System.out.println("客户端启动成功");
        Scanner sc = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream()) { // 获取输入流，服务器的响应数据
            try (OutputStream outputStream = socket.getOutputStream()) { // 获取输出流，向服务器传输的请求内容
                Scanner respSc = new Scanner(inputStream); // 这里面是服务器返回的数据，响应
                while (true) {
                    System.out.print("-> ");
                    String request = sc.next();
                    PrintWriter writer = new PrintWriter(outputStream); // 格式化输出
                    writer.println(request); // 将客户端输入的内容，作为请求发送出去
                    writer.flush(); // 可能数据会放入缓存区，将其刷新进去
                    // 从服务器端 获取响应
                    String response = respSc.nextLine();
                    System.out.printf("req: %s; resp: %s\n", request, response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1", 9090);
        client.start();
    }
}
