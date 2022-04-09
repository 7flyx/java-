package net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-09
 * Time: 19:51
 * Description: echo回显 客户端
 */
public class UdpEchoClient {
    private DatagramSocket socket;
    private String serverIp; // 服务器的ip地址
    private int serverPort; // 服务器的端口号

    // 需要指定服务器的ip和端口号，不需要指定本机的ip和端口号
    // 客户端的ip就是本机的ip，端口号是由操作系统自动分配的
    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    public void start() throws IOException {
        System.out.println("客户端开启成功");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("-> ");
            String request = sc.next();
            if (request.equals("exit")) {
                System.out.println("退出成功");
                break;
            }
            // 向服务器发送请求
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(StandardCharsets.UTF_8),
                    request.getBytes(StandardCharsets.UTF_8).length,
                    InetAddress.getByName(serverIp), // 通过InetAddress转换IP地址
                    serverPort); // 服务器端口号
            socket.send(requestPacket);

            // 读取服务器的响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            // 打印日志
            System.out.printf("req: %s; resp: %s\n", request, response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient udpEchoClient = new UdpEchoClient("127.0.0.1", 9090);
        udpEchoClient.start();
    }
}
