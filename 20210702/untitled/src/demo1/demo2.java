package demo1;

import java.util.Random;

public class demo2 {
    public static void main(String[] args) {
        int limit = 0;
        int colLim = 0;
        int leftLim = 0;
        int rightLim = 0;

        for (int i = 0; i < 20; i++) {
            limit = (int)(10 * Math.random());
            colLim = (int)(10 * Math.random());
            leftLim = (int)(10 * Math.random());
            rightLim = (int)(10 * Math.random());
            int pos1 = limit & (~(colLim | leftLim | rightLim));
            int pos2 = limit ^ (colLim | leftLim | rightLim);
            System.out.println("pos1: " + pos1 + "。" +
                    "参数为 limit= " + limit + " colLim= " + colLim + " leftLim= " + leftLim + " rightLim= " + rightLim);
            System.out.println("pos2: " + pos2 + "。" +
                    "参数为 limit= " + limit + " colLim= " + colLim + " leftLim= " + leftLim + " rightLim= " + rightLim);
            System.out.println();
        }
    }
}
