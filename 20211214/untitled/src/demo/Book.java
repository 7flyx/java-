package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:42
 * Description: 图书类
 */
public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private int isBorrow; //1表示已经借出，0表示没有

    public Book(int id, String name, String author, double price, int isBorrow) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isBorrow = isBorrow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(int isBorrow) {
        this.isBorrow = isBorrow;
    }
}
