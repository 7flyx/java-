package demo2;

public class TercherDemo {
    public void CheckSorce(int sorce) throws Exception{  //方法后抛出异常，会在调用这个方法的地方出现编译时错误
        if(sorce < 0 || sorce > 100){
            //创建异常对象---无参构造异常对象，默认输出的是 at demo2.TercherDemo.CheckSorce(TercherDemo.java:7)
                                                //	at demo2.ExceptionDemo3.main(ExceptionDemo3.java:15)
           // throw new SorceException(); //这里抛出异常，这个方法就要抛出异常
            throw new SorceException("分数有误，应在0-100之间");
        } else {
            System.out.println("分数正常");
        }
    }
}
