package net;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-04-10
 * Time: 21:25
 * Description: 查询每个键，返回对应的值
 */
public class UdpDictEchoServer extends UdpEchoServer{
    private HashMap<String, String> map;

    public UdpDictEchoServer(int port) throws SocketException {
        super(port);
        map = new HashMap<>();
        map.put("cat", "猫");
        map.put("dog", "狗");
        map.put("mouse", "老鼠");
        map.put("horse", "马");
    }

    @Override
    public String getResponse(String request) {
        return map.getOrDefault(request, "没查询到");
    }

    public static void main(String[] args) throws IOException {
        UdpDictEchoServer udpDictEchoServer = new UdpDictEchoServer(9090);
        udpDictEchoServer.start();
    }
}
