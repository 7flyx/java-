/**
 * Created by flyx
 * Description:
 * User: 听风
 * Date: 2021-09-20
 * Time: 12:17
 */
public class Demo {
    public static void main(String[] args) {
        //以下代码，是如何进行拼接字符串的？具体流程？
//        String str1 = "hello ";
//        String str2 = str1 + "world";

//        String str = "hello world";
//        StringBuilder sb = new StringBuilder(str);
//       // str = sb.reverse().toString();
//        System.out.println(str);
//        sb.delete(1, 4);
//        System.out.println(sb.toString());
        StringBuilder sb = new StringBuilder("I you");
        sb.insert(2, "love ");
        System.out.println(sb.toString());

    }
}
