import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by flyx
 * Description:  九宫格，生成5个随机数，在1~9之间，九宫格如下：
 * num | +  | num
 * - | num | -
 * num | +  | num
 *
 * 规则，只能走3步， 每次只能上下左右走，根据递归函数，算出走3步的各个位置的总和，
 * 并随机输出其中的一个数给用户。
 * User: 听风
 * Date: 2021-09-20
 * Time: 20:08
 */

public class Demo {
    private static ArrayList<Record> set = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>(); //保存随机生成的5个数
        while (list.size() != 5) {
            int num = (int) (Math.random() * 10);
            if (num != 0 && !list.contains(num)) {
                list.add(num);
            }
        }

        int[][] rect = printRect(list);

        randomSum(rect); //生成随机数的总和，并保存相应路径

        int index = (int)(Math.random() * 2560); //生成随机值，在表中拿去数据
        Record record = set.get(index);

        System.out.println(record.sum);
        System.out.println("相关路径如下：");
        System.out.println(record.path);

        System.out.print("请输入答案（比如：4+6-9+3）：");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String res = in.readLine();
        char[] chars = res.toCharArray(); //生成字符数组，便于拿去数值
        String[] numbers = res.split("-|\\+"); //以加减号进行分割

        //判断每个数是不是都在list里面，若不是，则答案错误
        if(checkNumbers(numbers, list)) {

            int sum = chars[0] - '0';
            int j = 1; //指向每个运算符的下标
            for (int i = 0; i < 3; i++) {
                if (chars[j] == '+') {
                    sum += (chars[j + 1] - '0');
                } else if (chars[j] == '-'){
                    sum -= (chars[j + 1] - '0');
                } else {
                    System.out.println("运算符错误！！！");
                }
                j += 2; //向后走两步
            }

            if (sum == record.sum) {
                System.out.println("回答正确");
            } else {
                System.out.println("回答错误");
            }
        } else {
            System.out.println("回答错误");
        }
    }

    /**
     * 用于记录每个随机数以及路径
     */
    private static class Record {
        public int sum;
        public String path;

        public Record(int sum, String path) {
            this.sum = sum;
            this.path = path;
        }
    }

    public static int[][] printRect(List<Integer> list) {
        if (list == null) {
            throw new RuntimeException("list is null.");
        }

        int[][] rect = {
                {list.get(0), '+', list.get(1)},
                {'-', list.get(2), '-'},
                {list.get(3), '+', list.get(4)}};

        System.out.printf("%d | + | %d\n", list.get(0), list.get(1));
        //System.out.print("_________\n");
        System.out.printf("- | %d | -\n", list.get(2));
        // System.out.print("_________\n");
        System.out.printf("%d | + | %d\n", list.get(3), list.get(4));
        return rect;
    }

    /**
     *  生成从五个位置出发的所有结果，保存在成员变量set中
     * @param rect 矩阵存储的数值信息
     */
    public static void randomSum(int[][] rect) {
        if (rect == null) {
            throw new RuntimeException("rect is null.");
        }

        calcSum(rect, 0, 0, 0, 0, new int[7]); //左上角开始
        calcSum(rect, 0, 2, 0, 0, new int[7]); //右上角开始
        calcSum(rect, 2, 0, 0, 0, new int[7]); //左下角开始
        calcSum(rect, 2, 2, 0, 0, new int[7]); //右下角开始
        calcSum(rect, 1, 1, 0, 0, new int[7]); //中间开始

//        for (Record record : set) {
//            System.out.print(record.sum + "  ");
//            System.out.println(record.path);
//        }
//        System.out.println("共有: " + set.size() + "种结果");
    }

    /**
     *
     * @param rect 矩阵，保存着每个位置的数值
     * @param row 当前位置的行
     * @param col 当前位置的列
     * @param count 到目前位置为止，加过几个数值了
     * @param indexOfPath 指向path数组的下标
     * @param path 存储沿途的路径
     */
    private static void calcSum(int[][] rect, int row, int col, int count, int indexOfPath, int[] path) {
        if (count == 4) {
            //分支限界，防止出现2-2-2-2的情况，也就是走到运算符的时候，又走回来了
            if (path[0] == path[2] || path[2] == path[4] || path[4] == path[6]) {
                return;
            }

            int sum = path[0];
            StringBuilder sb = new StringBuilder(String.valueOf(sum)); //存储相关路径
            int index = 1; //指向path数组的运算符的下标
            for (int j = 0; j < 3; j++) {
                if (path[index] == '+') {
                    sum += path[index + 1];
                    sb.append("+").append(path[index + 1]);
                } else {
                    sum -= path[index + 1];
                    sb.append("-").append(path[index + 1]);
                }
                index += 2;
            }

            //生成记录，并保存
            Record record = new Record(sum, sb.toString());
            set.add(record);
            return;
        }
        if (row > 2 || row < 0 || col < 0 || col > 2) { //出了矩阵外，提前返回
            return;
        }

        path[indexOfPath] = rect[row][col];

        if (rect[row][col] != '+' && rect[row][col] != '-') { //不是运算符
            count++;
        }
        calcSum(rect, row, col + 1, count, indexOfPath + 1, path); //向右
        calcSum(rect, row, col - 1, count, indexOfPath + 1, path); //向左
        calcSum(rect, row + 1, col, count, indexOfPath + 1, path); //向下
        calcSum(rect, row - 1, col, count, indexOfPath + 1, path); //向上
    }

    public static boolean checkNumbers(String[] numbers, ArrayList<Integer> list) {
        if (numbers == null) {
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                if (numbers[i].equals(String.valueOf(list.get(j)))) {
                    flag = true;
                    break;
                }
            }

            if (!flag) { //一次也没相等的情况
                return false;
            }
        }
        return true;
    }
}
