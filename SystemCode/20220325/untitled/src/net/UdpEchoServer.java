package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

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

    public void start() {
        DatagramPacket requestPacket = new DatagramPacket(new byte[4096], 4096);
        try {
            socket.receive(requestPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
