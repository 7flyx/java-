

// public class 强制类型转换 {
	// public static void main(String[] args) {
		// // //自动类型转换
		// // double a = 10;
		// // System.out.println(a);
		
		// // //强制类型转换
		// // int b = (int)88.0;
		// // System.out.println(b);
		
		
		// //字符串的‘+’操作
		// System.out.println("heima"+666);  //数值在字符串的后面，将会直接组合成字符串
		// System.out.println("666"+"heima");
		// System.out.println(1+99+"heima"); //两位数字在字符串前面，将先把数值进行加法运算后，再与字符串组合成字符串
		
		
		// //逻辑与&，逻辑或|      ----与C语言的有点区别
		// //短路与&&，短路或||，  这个与C语言中的一样的，只是名字不一样
		// //这两种的效果是一样的，前者无论判断的第一个真假，两个表达式都会执行
		// //后者，第一个表达式的真假，可能会影响到第二个表达式的运行
	// }
// }



public class 强制类型转换 {
	public static void main(String[] args) {
		int weight1 = 180;
		int weight2 = 200;
		boolean d = (weight1 == weight2)?true:false;
		
		System.out.println(d);
	}
}