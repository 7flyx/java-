

import java.util.Scanner;
public class scannerTest2 {
	public static void main(String[] args){
		
		// System.out.println("请输入分数：");
		// Scanner sc = new Scanner(System.in);
		
		// int x = sc.nextInt();
		
		// if(x<0 || x>100)
			// System.out.println("输入的数据有误");
		// else if( x>=95 && x<=100)
			// System.out.println("奖励山地自行车一辆");
		// else if( x>=80 && x<= 95)
			// System.out.println("变形金刚一套");
		// else
			// System.out.println("胖揍一顿");
		
		
		// for(int i=0;i<5;i++) {
			// System.out.println("hello world");
		// }
		
		// int sum= 0;
		// //求1到100之间的偶数和
		// for(int i=2;i<=100;i+=2) {
			// sum += i;
		
		// }
			// System.out.println("1-100之间的偶数为" + sum);
		
		
		//求三位的所有水仙花数
		int ge,shi,bai;
		for(int i=100;i<1000;i++) {
			ge = i % 10;
			shi = i /10 %10;
			bai = i/100%10;
			if(ge*ge*ge + shi*shi*shi + bai*bai*bai == i) {
				System.out.println(i);
			}
		}
	}
}