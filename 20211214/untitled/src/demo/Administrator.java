package demo;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-12-14
 * Time: 16:51
 * Description: 1增加，2删除，3查找，4修改。
 * 用户可以拿到它对应的数组，数组中有相关的操作。每个操作对应一个下标值
 */
public class Administrator extends Person {

    private static final IOption[] options;

    static {
        options = new IOption[]{new AddOption(), //新增图书
                new DelOption(), //删除图书
                new ModifyOption(),//修改图书
                new FindOption(),//查找图书
        };
        //填写相应的操作接口
    }

    public Administrator(int id, String name) {
        super(id, name, options);
    }
}
