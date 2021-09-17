package book;

/**
 * 书籍信息
 * @author Administrator
 */
public class Book {
	private String name;
	private String author;
	private double price;
	private boolean isBorrowed;
	
	public Book(String name, String author, double price, boolean isBorrowed) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.isBorrowed = isBorrowed;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean getIsBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("书名：").append(name).append("\t");
		sb.append("作者：").append(author).append("\t");
		sb.append("价格：").append(price).append("\t");
		sb.append(isBorrowed? "已借出" : "未借出");
		return sb.toString();
	}
}
