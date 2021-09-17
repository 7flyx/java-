package book;

/**
 * 书架
 * @author Administrator
 *
 */
public class BookList {
	public Book[] bookList;
	public int listSize; //当前有多少本书
	
	public BookList(int size) {
		bookList = new Book[size];
		bookList[0] = new Book("三国演义", "罗贯中", 29.9, false);
		bookList[1] = new Book("红楼梦", "曹雪芹", 28.9, false);
		bookList[2] = new Book("西游记", "吴承恩", 39.9, false);
		listSize = 3;
	}
}
