package demo7;

public class demo {
    public static void main(String[] args) {
        //测试类---math--本身是没有构造方法的，用static修饰，也就是说可以直接拿类名直接进行调用

        //abs（），将数值转换为正数，即就是绝对值的作用
        System.out.println(Math.abs(-22)); //22
        System.out.println(Math.abs(22));
        System.out.println("-----------");

        //floor（）， 将数值取到小于等于他的最大值
        System.out.println(Math.floor(12.22)); //12
        System.out.println(Math.floor(12.53));
        System.out.println("-----------");

        //ceil（）， 取到大于等于他的最小值
        System.out.println(Math.ceil(12.49)); //13
        System.out.println(Math.ceil(12.49));

        //round(), 四舍五入,放回的是int类型
        System.out.println(Math.round(12.49)); //12
        System.out.println(Math.round(12.59)); //13

        //random(), 生成的是0.0-1.0   之间的随机数，这里左闭右开区间
        System.out.println(Math.random());  //返回值类型是double
        System.out.println((int)(Math.random()*100)); //生成的是0-99之间的整数


    }
}
