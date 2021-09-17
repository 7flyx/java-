package users;

import book.BookList;
import operation.IOperation;

/**
 *
 * @author Administrator
 *
 */
public abstract class User {
	protected String name;
	protected IOperation[] iOperation;
	
	public User(String name) {
		this.name = name;
	}
	
	public void doOperation(int choice, BookList list) {
		this.iOperation[choice].work(list);
	}

	public abstract int menu();

}
