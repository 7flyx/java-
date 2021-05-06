package demo2;

public class SorceException extends Exception{

    //只需给出构造方法即可，其他的会在父类中有
    public SorceException(){
    }

    public SorceException (String message) {
        super(message);
    }
}
