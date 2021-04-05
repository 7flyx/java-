package test;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Phone {
    //类的定义   ---  有点像C语言中的结构体

    //成员变量
    String brand;  //品牌
    int price; //价格

    //成员方法---注意：不需要使用static
    public void call() {
        System.out.println("打电话");
    }
    public void sendMess() {
        System.out.println("发短信");
    }

}
