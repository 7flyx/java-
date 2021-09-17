package operation;

import java.util.Scanner;

import book.Book;
import book.BookList;

public class AddOperation implements IOperation {
	public void work(BookList list) {
		Scanner sc = new Scanner(System.in);
		System.out.println("添加图书");
		System.out.print("请输入书名：");
		String bookName = sc.next();
		
		System.out.print("请输入作者：");
		String author = sc.next();
		
		System.out.print("请输入价格：");
		double price = sc.nextDouble();
		
		int size = list.listSize++;
		list.bookList[size] = new Book(bookName, author, price, false);
		System.out.println("添加成功");
	}
}
