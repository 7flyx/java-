/**
 * Created by Terry
 * User: Administrator
 * Date: 2021-10-24
 * Time: 9:35
 * Description:
 */
class Algorithm <T extends Comparable<T>>{

    public T findMax(T[] array) {
        T tmp = array[0];
        for (int i = 1; i < array.length; i++) {
            if (tmp.compareTo(array[i]) > 1) {
                tmp = array[i];
            }
        }
        return tmp;
    }
}

class Algorithm2 {
    public static<T extends Comparable<T>>  T findMax(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
        }
        return max;
    }
}


public class Demo {

}
