package net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-12
 * Time: 14:30
 * Description:
 */
public class UdpEchoClient {
    // 数据报的socket
    private DatagramSocket datagramSocket;
    // 此处的服务器IP和端口号，是在构造数据报的时候，进行填写的
    private String serverIp; // 服务器的ip
    private int serverPort; // 服务器的端口号

    public UdpEchoClient(String serverIp, int serverPort) throws SocketException {
        this.serverPort = serverPort;
        this.serverIp = serverIp;
        this.datagramSocket = new DatagramSocket();
    }

    public void start() throws IOException {
        System.out.println("客户端启动成功");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("-> ");
            String request = sc.next();
            if (request.equals("exit")) {
                System.out.println("退出");
                break;
            }

            // 根据输入的数据，构造请求
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(StandardCharsets.UTF_8),
                    request.getBytes(StandardCharsets.UTF_8).length,
                    InetAddress.getByName(serverIp), serverPort); // 服务器的IP地址和端口号
            datagramSocket.send(requestPacket);

            // 接收响应数据
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096], 4096);
            datagramSocket.receive(responsePacket); // 接收服务器的响应数据
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            // System.out.println(responsePacket.getLength());
            System.out.printf("req: %s; resp: %s\n", request, response);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client = new UdpEchoClient("127.0.0.1", 9090);
        client.start();
    }

}
