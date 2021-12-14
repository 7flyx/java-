package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 17:14
 * Description: 1、查询图书， 2、借阅图书， 3、归还图书
 */
public class OrdinaryUser extends Person {
    private static final IOption[] options;

    static  {
        options = new IOption[] {
                new FindOption(), //查抄图书
                new BorrowOption(), //借阅图书
                new ReturnOption() //归还图书
        };
    }

    public OrdinaryUser(int id, String name) {
        super(id, name, options);
    }
}
