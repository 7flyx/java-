package operation;

import book.BookList;

import java.util.Scanner;

public class SearchOperation implements IOperation {
	public void work(BookList list) {
		System.out.println("查询图书");
		Scanner sc = new Scanner(System.in);

		System.out.print("请输入书名：");
		String bookName = sc.next();
		int length = list.listSize;
		for (int i = 0; i < length; i++) {
			if (list.bookList[i].getName().equals(bookName)) {
				System.out.println("查询成功");
				return;
			}
		}
		System.out.println("查询失败，书架并没有 " + bookName + " 这本书籍!");
	}
}
