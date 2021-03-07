

public class Type {
	public static void main(String[] args) {
		//type 占用一个字节的内存空间
		byte a=30;
		System.out.println(a);
		
		//int short long等等
		int b = 60;
		System.out.println(b);
		
		//long 类型注意事项
		//Java中，整形变量的默认类型是int，当一个数值超过int的范围时，需要在数值后面加入'L'.
		long c = 10000000000L;
		System.out.println(c);
		
		//浮点型 float 注意事项----Java中，浮点型的数值，默认是double类型的，所以float类型输出时
		//数值后面需要加'F'
		float d=3.14F;
		System.out.println(d);
		
		//char 类型----与C语言中的char有区别
		//C语言中char是1个字节，Java中是2个字节，而且Java中的char还是无符号数，范围是0-65535
		//字符类型，赋值应给字符，或者相应的ASCII码值
		char e= 65;
		System.out.println(e);
	}
}