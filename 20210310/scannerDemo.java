


//scanner的使用，导包，创建对象，接收数据

import java.util.Scanner;   //导包
public class scannerDemo {
	public static void main(String[] args) {
		//创建对象---sx为变量名
		Scanner sc = new Scanner(System.in);
		
		//接收数据
		int x = sc.nextInt();
		
		//输出数据
		System.out.println("x:"+x);
	}
}