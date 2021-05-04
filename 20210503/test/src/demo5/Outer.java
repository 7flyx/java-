package demo5;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Outer {

    private int num = 20;

    public void method() {

        //匿名局部类---实则跟C语言里的匿名结构体差不多，只不过这里是类
        new Inter() {
            public void show() {
                System.out.println("匿名局部类");
            }
        }.show();

          //可也使用具体的父类进行类型的接收，在有变量名进行调用
        Jumpping j = new Jumpping() {
            public void jump() {
                System.out.println("跳高");
            }
        };
        j.jump();
    }
}
