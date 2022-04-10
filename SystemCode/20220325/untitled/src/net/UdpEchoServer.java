package net;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-06
 * Time: 21:08
 * Description: UDP 回显
 */
public class UdpEchoServer {
    private DatagramSocket socket = null;

    // 形参就是端口号
    public UdpEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动成功");
        while (true) {
            // 1、读取客户端发送过来的请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
            // 在请求发送过来之前，这里是进行阻塞等待的
            socket.receive(requestPacket);
            // 将请求中的数据，转换为字符串，特别注意，这里的第三个参数，是当前byte数组里面内容的长度
            String  request = new String(requestPacket.getData(), 0, requestPacket.getLength());

            // 2. 根据请求中的数据，计算出响应，这里就简单的进行回显输出请求数据即可，代替正常的业务
            String response = getResponse(request);

            // 3. 将计算出来的响应返回给客户端
            // 还是通过DatagramPacket对象，进行包装，再通过socket进行返回
            // 第一个参数是byte数组，第二个参数是数组长度，第三个参数是地址
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(StandardCharsets.UTF_8),
                    response.getBytes(StandardCharsets.UTF_8).length, requestPacket.getSocketAddress());
            socket.send(responsePacket);
            // 打印日志--这里输出端口号是客户端的端口号
            System.out.printf("[%s:%d] req: %s; resp: %s\n", requestPacket.getAddress().toString(),
                    requestPacket.getPort(), request, response);
        }

    }

    public String getResponse(String request) {
        return  request;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer udpEchoServer = new UdpEchoServer(9090); // 端口号
        udpEchoServer.start(); // 开启服务器
    }
}
