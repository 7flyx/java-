package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-12
 * Time: 18:46
 * Description:
 */
public class TcpEchoClient {
    private Socket socket;

    public TcpEchoClient(String serverIp, int serverPort) throws IOException {
        socket = new Socket(serverIp, serverPort);
    }

    public void start() {
        System.out.println("客户端启动成功");
        Scanner sc = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream()) {
            try (OutputStream outputStream = socket.getOutputStream()) {
                Scanner inputSc = new Scanner(inputStream); // 处理服务器返回的响应数据
                while(true) {
                    // 输入请求的数据
                    System.out.print("-> ");
                    String request = sc.next();
                    if (request.equals("exit")) {
                        System.out.println("退出");
                        break;
                    }

                    // 将输入的数据，发送到服务器端
                    PrintWriter writer = new PrintWriter(outputStream);
                    writer.println(request);
                    writer.flush();

                    // 从输入流中读取 服务器返回的响应
                    String response = inputSc.next();
                    System.out.printf("req: %s; resp: %s\n", request, response);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1", 9090);
        client.start();
    }

}
