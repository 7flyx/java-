package operation;

import book.BookList;

import java.util.Scanner;

public class ReturnOperation implements IOperation {
	public void work(BookList list) {
		System.out.println("归还图书");
		Scanner sc = new Scanner(System.in);

		System.out.print("请输入书名：");
		String bookName = sc.next();
		int length = list.listSize;
		for (int i = 0; i < length; i++) {
			if (list.bookList[i].getName().equals(bookName)) {
				list.bookList[i].setBorrowed(false);
				System.out.println("归还成功");
				return;
			}
		}
		System.out.println("书架并没有 " + bookName + " 这本书籍!");
	}
}
