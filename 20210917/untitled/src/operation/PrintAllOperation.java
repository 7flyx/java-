package operation;

import book.BookList;

import java.util.Arrays;

public class PrintAllOperation implements IOperation {
	public void work(BookList list) {
		int length = list.listSize;
		for (int i = 0; i < length; i++) {
			System.out.println(list.bookList[i].toString());
		}
	}
}
