package users;

import java.util.Scanner;

import operation.BorrowOperation;
import operation.ExitOperation;
import operation.IOperation;
import operation.ReturnOperation;
import operation.SearchOperation;

public class NormalUser extends User {
	public NormalUser(String name) {
		super(name);
		this.iOperation = new IOperation[] {
				new ExitOperation(),
				new SearchOperation(),
				new BorrowOperation(),
				new ReturnOperation()
		};
	}
	
	public int menu() {
		System.out.println("hello " + this.name);
		System.out.println("========菜单栏===========");
		System.out.println("1、查询图书");
		System.out.println("2、借阅图书");
		System.out.println("3、归还图书");
		System.out.println("0、退出系统");
		System.out.println("=======================");
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			choice = sc.nextInt();
			if (choice >= 0 && choice <= 3) {
				break;
			}
			System.out.println("输入参数非法，请重新输入:");
		}
		return choice;
	}
}
