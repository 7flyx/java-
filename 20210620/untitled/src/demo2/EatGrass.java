package demo2;

public class EatGrass {
    public static void main(String[] args) {
        //打表问题
        //牛羊吃草，羊先吃，牛后吃，输入一个整数N，表示共有N份草
        //牛和羊，每一轮都只能吃  1   4   16  64  4 的n次方  份草，它两个轮流吃
        //问，吃到最后，谁是最后一个吃完草的

        //先暴力解出答案，（打表），然后找规律


    }

    public static String winner1(int N) {

        //0  1  2  3  4
        //后 先 后  先  先  。计算的先手和后手，实则就是牛羊
        if (N < 5) {
            return (N == 0 || N == 2) ? "后手" : "后手";
        }

        int num = 1; //1份草，代表每轮需要吃的草的数量
        while (num <= N) {
            //再次递归调用，判断N - num份草
            if (winner1(N - num).equals("后手")) {
                return "先手";
            }

            if (num > N / 4) {
                break; //防止溢出
            }
            num *= 4;
        }
        return "后手";
    }

    public static String winner2(int N) {
        //根据暴力解法，找出的规律
        if (N % 5 == 0 || N % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }
}
