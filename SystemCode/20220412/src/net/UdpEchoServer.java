package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-12
 * Time: 14:11
 * Description: 基于UDP协议的回显 服务器
 *  UDP是面向报文的，所使用的是一种数据报
 */
public class UdpEchoServer {
    // 数据报的Socket
    private DatagramSocket datagramSocket;

    public UdpEchoServer(int port) throws SocketException {
        datagramSocket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动成功");
        // 死循环，一直需要接收来自客户端的请求
        while (true) {
            // 接收来自客户端的请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096); // 以参数的形式，用于存储数据的
            datagramSocket.receive(requestPacket); // 如果客户端暂时还未发送请求过来，这里会阻塞等待
            // 请求的数据
            String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
            // 这里根据一定的流程，计算出响应
            String response = process(request);
            // 根据计算出来的响应，构造新的数据报进行返回
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(StandardCharsets.UTF_8),
                    response.getBytes(StandardCharsets.UTF_8).length, requestPacket.getSocketAddress()); // 第三个参数是客户端的地址
//            System.out.println("===================");
//            System.out.println(requestPacket.getAddress().toString()); // 这是不带端口号的，就是一个ip地址。/127.0.0.1
//            System.out.println(requestPacket.getSocketAddress().toString()); // 这个是带有端口号的。/127.0.0.1:56785
//            System.out.println("===================");
            datagramSocket.send(responsePacket); // 将响应数据报 返回给客户端
            System.out.printf("[%s:%d] req: %s; resp: %s\n", requestPacket.getAddress().toString(), requestPacket.getPort(),
                    request, response);
        }
    }

    // 根据请求的数据，计算出响应
    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server = new UdpEchoServer(9090);
        server.start();
    }
}
