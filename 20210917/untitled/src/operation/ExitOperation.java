package operation;

import book.BookList;

public class ExitOperation implements IOperation {
	public void work(BookList list) {
		System.exit(0);
	}
}
