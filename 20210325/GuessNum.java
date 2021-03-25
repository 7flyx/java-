
//导包
import java.util.Random;
import java.util.Scanner;


public class GuessNum {
	public static void main(String[] args) {
		
		//创建随机数Random
		Random r = new Random();
		int num = r.nextInt(100) + 1;
		
		
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入你要猜的数字:");
			int guessnum = sc.nextInt();
			
			if(num > guessnum) {
				System.out.println("你猜的数字"+ guessnum + "小了");
			} else if(num<guessnum) {
				System.out.println("你猜的数字" + guessnum + "大了");
			} else {
				System.out.println("恭喜你猜中了");
				break;
			}				
		}
	}
}