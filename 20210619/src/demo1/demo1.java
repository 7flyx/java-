package demo1;

import java.util.ArrayList;
import java.util.List;

public class demo1 {
    public static void main(String[] args) {
        //计算party的最大快乐值
        //1. 如果一个员工来了，他的直接下级不能来
        //将一个x结点分为两种情况，即就是来与不来，不来的情况下，他的下级可以来也可以不来
    }

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }
    }

    public static class info {
        public int yes; //要去参加party的快乐值
        public int no; //不去参加party的快乐值

        public info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy(Employee e) {
        if (e == null) {
            return 0;
        }
        info result = process(e);
        return Math.max(result.no, result.yes);
    }

    public static info process(Employee e) {
        if (e.nexts.isEmpty()) { //已经是基层员工了，直接返回自己的快乐值，或者自己不去的时的快乐值
            return new info(e.happy, 0);
        }

        //计算去与不去时的数值
        int yes = e.happy;
        int no = 0;
        for (Employee next : e.nexts) {
            info nextInfo = process(next);
            yes += nextInfo.no; //当前结点要去的话，只能加上下级结点不去的情况
            no += Math.max(nextInfo.yes,nextInfo.no); //这个结点不去的话，他的下级结点可去可不去，取最大值
        }

        return new info (yes,no);
    }
}
