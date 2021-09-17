package operation;

import book.BookList;

import java.util.Scanner;

public class DelOperation implements IOperation {
	public void work(BookList list) {
		System.out.println("删除书籍");
		Scanner sc = new Scanner(System.in);

		System.out.print("请输入书名：");
		String bookName = sc.next();
		int length = list.listSize;
		for (int i = 0; i < length; i++) {
			if (list.bookList[i].getName().equals(bookName)) {
				list.bookList[i] = list.bookList[length-1]; //将最后一本书放到这个位置，并且容量减1
				list.listSize--;
				return;
			}
		}
		System.out.println("删除失败，书架并没有 " + bookName + " 这本书籍!");
	}
}
