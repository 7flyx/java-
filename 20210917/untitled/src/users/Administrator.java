package users;

import java.util.Scanner;

import operation.AddOperation;
import operation.DelOperation;
import operation.ExitOperation;
import operation.IOperation;
import operation.PrintAllOperation;
import operation.SearchOperation;

/**
 * 管理员账户
 * @author Administrator
 *
 */
public class Administrator extends User{
	
	public Administrator(String name) {
		super(name);
		this.iOperation = new IOperation[] {
				new ExitOperation(),
				new AddOperation(),
				new DelOperation(),
				new SearchOperation(),
				new PrintAllOperation()
		};
	}
	
	public int menu() {
		System.out.println("hello " + this.name);
		System.out.println("========菜单栏===========");
		System.out.println("1、添加图书");
		System.out.println("2、删除图书");
		System.out.println("3、查询图书");
		System.out.println("4、打印所有图书");
		System.out.println("0、退出系统");
		System.out.println("=======================");
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			choice = sc.nextInt();
			if (choice >= 0 && choice <= 4) {
				break;
			}
			System.out.println("输入参数非法，请重新输入:");
		}
		return choice;
	}
}
