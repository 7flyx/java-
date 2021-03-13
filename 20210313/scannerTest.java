


import java.util.Scanner; //导包 
public class scannerTest {
	public static void main(String[] args) {
		
		//输入三个和尚的身高，并输出最高的值---导包
		Scanner sc = new Scanner(System.in);
		
		System.out.println("请输入数值:");
		int height1 = sc.nextInt();
		int height2 = sc.nextInt();
		int height3 = sc.nextInt();
		
		
		int tmp = height1 > height2 ? height1 : height2;
		int max = tmp > height3 ? tmp : height3;
		 
		System.out.println("max height:" + max);
	}
}