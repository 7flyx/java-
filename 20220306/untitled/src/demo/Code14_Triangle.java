package demo;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-03-23
 * Time: 10:44
 * Description: 3月23号 第二个代码题 三角形
 */
public class Code14_Triangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = new String[3];

        int i = 5;
        int s = (i++) + (++i) + (i--) + (--i);
        System.out.println(s);
//        System.out.println(getStringNumSum(sc.next(), sc.next()));

//        for (int i = 0; i < 3; i++) {
//            arr[i] = sc.next();
//        }
//
//        Arrays.sort(arr, new compareStringNum()); // 排升序
//        String sum = getStringNumSum(arr[0], arr[1]);
//        if (new compareStringNum().compare(sum, arr[2]) > 0) {
//            System.out.println("Yes");
//        } else {
//            System.out.println("No");
//        }

    }

    public static class compareStringNum implements Comparator<String> {
        public int compare(String o1, String o2) {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        }
    }

    public static String getStringNumSum(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int N = s1.length();
        int M = s2.length();
        int i1 = N - 1; // 指向s1
        int i2 = M - 1; // 指向s2
        int carry = 0; //进位
        while (i1 >= 0 && i2 >= 0) {
            int sum = (s1.charAt(i1) - '0') + (s2.charAt(i2) - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i1--;
            i2--;
        }

        if (i1 >= 0) { // s1更长的情况
            while (carry != 0 && i1 >= 0) {
                int sum = s1.charAt(i1) - '0' + carry;
                sb.append(sum % 10);
                carry = sum / 10;
                i1--;
            }
            sb.reverse().insert(0, s1.substring(0, i1 + 1)); // 左闭右开区间
            return sb.toString();
        }

        // s2更长的情况
        while (carry != 0 && i2 >= 0) {
            int sum = s2.charAt(i2) - '0' + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i2--;
        }
        sb.reverse().insert(0, s2.substring(0, i2 + 1)); // 左闭右开区间
        return sb.toString();
    }



    // 调用API接口
    public static void main1(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            BigInteger a=sc.nextBigInteger();
            BigInteger b=sc.nextBigInteger();
            BigInteger c=sc.nextBigInteger();
            if(a.add(b).compareTo(c)>0 && a.add(c).compareTo(b)>0 && b.add(c).compareTo(a)>0)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

}
