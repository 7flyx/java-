package class01;

import java.util.Arrays;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:13
 * Description: 给定两个非负数组x和hp，长度都是N，再给定一个正数range
 * x有序，x[i]表示i号怪兽在x轴上的位置
 * hp[i]表示i号怪兽的血量
 * 再给定一个正数range，表示如果法师释放技能的范围长度。法师必须站在X位置，能打到[X-range, X+range]范围。
 * 也就是说，法师技能的中心点必须落在X位置上，即中心点落再怪兽身上。
 * 被打到的每只怪兽损失1点血量。返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
 */
public class Code06_AOE {
    /**
     * 这道题本质上就是 在数组上删除元素，最终的目的就是将数组全部清为0。自然就想到了 “线段树”。
     * 至于具体怎么用这个线段树，就是需要搭配贪心来使用：以每个位置作为 这个AOE范围伤害的最左边界，
     * 向右进行扩展。
     *
     * @param x     怪兽的位置
     * @param hp    怪兽的血量
     * @param range 技能的范围
     * @return 返回最少需要多少次AOE技能，能清空全部的怪兽
     */
    public static int countAOE(int[] x, int[] hp, int range) {
        if (x == null || hp == null || range <= 0) {
            return 0;
        }
        int N = hp.length;
        // 以下都是预处理结构，提前计算出技能范围和技能中心点的位置
        // coverLeft[i]：如果以i为中心点放技能，左侧能影响到哪，下标从1开始，不从0开始
        // coverRight[i]：如果以i为中心点放技能，右侧能影响到哪，下标从1开始，不从0开始
        // coverLeft和coverRight数组，0位置弃而不用
        // 举个例子，比如 :
        // x = [1,2,5,7,9,12,15], range = 3
        // 下标: 1 2 3 4 5 6 7
        // 以1位置做中心点: 能覆盖位置:1,2 -> [1..2]
        // 以2位置做中心点: 能覆盖位置:1,2,3 -> [1..3]
        // 以3位置做中心点: 能覆盖位置:2,3,4 -> [2..4]
        // 以4位置做中心点: 能覆盖位置:3,4,5 -> [3..5]
        // 以5位置做中心点: 能覆盖位置:4,5,6 -> [4..6]
        // 以6位置做中心点: 能覆盖位置:5,6,7 -> [5..7]
        // 以7位置做中心点: 能覆盖位置:6,7 -> [6..7]
        // 可以看出如果从左往右，依次求每个位置的左边界(left)和左边界(right)，是可以不回退的！
        int[] coverLeft = new int[N + 1];
        int[] coverRight = new int[N + 1];
        int left = 0;
        int right = 0;
        // 从左往右，不回退的依次求每个位置的左边界(left)和左边界(right)，记录到coverLeft和coverRight里
        for (int i = 0; i < N; i++) { // 以i位置为中心点，向左右两边进行扩展
            while (x[i] - x[left] > range) {
                left++;
            }
            while (right < N && x[right] - x[i] <= range) {
                right++;
            }
            coverLeft[i + 1] = left + 1; // 线段树是从1下标开始的，所以+1
            coverRight[i + 1] = right;
        }
        int[] best = new int[N + 1]; // 最好中心点
        int trigger = 0;
        for (int i = 0; i < N; i++) {
            while (trigger < N && x[trigger] - x[i] <= range) {
                trigger++;
            }
            best[i + 1] = trigger;
        }
        // 以上全是预处理结构
        SegmentTree segmentTree = new SegmentTree(hp);
        segmentTree.build(1, N, 1);
        int rightBoard = range * 2; // 左边界， 左边界+rightBoard就是右边界
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int leftEdge = segmentTree.query(i, i, 1, N, 1); // L和R整体向右走一个单位
            if (leftEdge > 0) { // 当前怪兽的血量还是大于0的时候
                ans += leftEdge;
                int t = best[i];
                int L = coverLeft[t];
                int R = coverRight[t];
                segmentTree.add(L, R, 1, N, 1, -leftEdge); // L和R整体向右走一个单位
            }
        }
        return ans;
    }

    private static class SegmentTree {
        private int length;
        private int[] arr; // 原数组，只是从下标1开始的
        private int[] sum; // 范围的累加和
        private int[] lazy; // lazy数组，累加懒惰标记。lazy[i] != 0，表示有懒惰值，需要向下分发
        private int[] change; // 更新值
        private boolean[] update; // 表示是否需要更新

        public SegmentTree(int[] origin) {
            this.length = origin.length + 1; // 下标为0位置，不用
            this.arr = new int[this.length];
            this.sum = new int[this.length << 2]; // 必须扩大4倍，以防后续访问越界
            this.lazy = new int[this.length << 2];
            this.change = new int[this.length << 2];
            this.update = new boolean[this.length << 2];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        /**
         * @param L  固定，被查询的左边界
         * @param R  固定，被查询的右边界
         * @param l  动态左边界
         * @param r  动态右边界
         * @param rt 二叉树的根节点位置
         * @return 返回l~r范围上的累加和
         */
        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) { // L ... l ...r....R
                return sum[rt];
            }
            int mid = (r + l) >> 1;
            pushDown(mid - l + 1, r - mid, rt); // 先向下分发之后，才能进行统计数据
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        // 根据arr数组，更新出sum数组的数据
        // l 从1开始
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt); // 向上整合数据
        }

        /**
         * @param L   固定，被修改的左边界
         * @param R   固定，被修改的右边界
         * @param l   当前左边界
         * @param r   当前右边界
         * @param rt  二叉树的根节点的位置
         * @param num 新的值
         */
        public void add(int L, int R, int l, int r, int rt, int num) {
            if (L <= l && R >= r) { // L...l...r...R
                lazy[rt] += num;
                sum[rt] += num * (r - l + 1);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt); // 先向下分发，有可能上次是change，直接改0了
            if (L <= mid) {
                add(L, R, l, mid, rt << 1, num);
            }
            if (R > mid) {
                add(L, R, mid + 1, r, rt << 1 | 1, num);
            }
            pushUp(rt);
        }

        // 向下分发数据
        private void pushDown(int lSize, int rSize, int rt) {
            if (update[rt]) {
                update[rt] = false;
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                sum[rt << 1] = change[rt] * lSize;
                sum[rt << 1 | 1] = change[rt] * rSize;
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * lSize;
                sum[rt << 1 | 1] += lazy[rt] * rSize;
                lazy[rt] = 0; // 归0
            }
        }

        // 向上整合数据
        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }
    }

    public static class SegmentTreeL {
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTreeL(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN]; // arr[0] 不用 从1开始使用
            for (int i = 1; i < MAXN; i++) {
                arr[i] = origin[i - 1];
            }
            sum = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围的累加和信息

            lazy = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
            change = new int[MAXN << 2]; // 用来支持脑补概念中，某一个范围有没有更新操作的任务
            update = new boolean[MAXN << 2]; // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
        }

        private void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        // 之前的，所有懒增加，和懒更新，从父范围，发给左右两个子范围
        // 分发策略是什么
        // ln表示左子树元素结点个数，rn表示右子树结点个数
        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                lazy[rt << 1] = 0;
                lazy[rt << 1 | 1] = 0;
                sum[rt << 1] = change[rt] * ln;
                sum[rt << 1 | 1] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[rt << 1] += lazy[rt];
                sum[rt << 1] += lazy[rt] * ln;
                lazy[rt << 1 | 1] += lazy[rt];
                sum[rt << 1 | 1] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        // 在初始化阶段，先把sum数组，填好
        // 在arr[l~r]范围上，去build，1~N，
        // rt : 这个范围在sum中的下标
        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                change[rt] = C;
                sum[rt] = C * (r - l + 1);
                lazy[rt] = 0;
                return;
            }
            // 当前任务躲不掉，无法懒更新，要往下发
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        // L..R -> 任务范围 ,所有的值累加上C
        // l,r -> 表达的范围
        // rt 去哪找l，r范围上的信息
        public void add(int L, int R, int C, int l, int r, int rt) {
            // 任务的范围彻底覆盖了，当前表达的范围
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 任务并没有把l...r全包住
            // 要把当前任务往下发
            // 任务 L, R 没有把本身表达范围 l,r 彻底包住
            int mid = (l + r) >> 1; // l..mid (rt << 1) mid+1...r(rt << 1 | 1)
            // 下发之前所有攒的懒任务
            pushDown(rt, mid - l + 1, r - mid);
            // 左孩子是否需要接到任务
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            // 右孩子是否需要接到任务
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            // 左右孩子做完任务后，我更新我的sum信息
            pushUp(rt);
        }

        // 1~6 累加和是多少？ 1~8 rt
        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

    }

    // 贪心策略：永远让最左边缘以最优的方式(AOE尽可能往右扩，最让最左边缘盖住目前怪的最左)变成0，也就是选择：
    // 一定能覆盖到最左边缘, 但是尽量靠右的中心点
    // 等到最左边缘变成0之后，再去找下一个最左边缘...
    public static int minAoe2(int[] x, int[] hp, int range) {
        int N = x.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (hp[i] > 0) {
                int triggerPost = i;
                while (triggerPost < N && x[triggerPost] - x[i] <= range) {
                    triggerPost++;
                }
                ans += hp[i];
                aoe(x, hp, i, triggerPost - 1, range);
            }
        }
        return ans;
    }

    public static void aoe(int[] x, int[] hp, int L, int trigger, int range) {
        int N = x.length;
        int RPost = trigger;
        while (RPost < N && x[RPost] - x[trigger] <= range) {
            RPost++;
        }
        int minus = hp[L];
        for (int i = L; i < RPost; i++) {
            hp[i] = Math.max(0, hp[i] - minus);
        }
    }

    // for test
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 500;
        int X = 10000;
        int H = 50;
        int R = 10;
        int time = 5000;
        System.out.println("test begin");
        for (int i = 0; i < time; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x = randomArray(len, X);
            Arrays.sort(x);
            int[] hp = randomArray(len, H);
            int range = (int) (Math.random() * R) + 1;
            int[] x2 = copyArray(x);
            int[] hp2 = copyArray(hp);
            int ans2 = minAoe2(x2, hp2, range);
            // 已经测过下面注释掉的内容，注意minAoe1非常慢，
            // 所以想加入对比需要把数据量(N, X, H, R, time)改小
//			int[] x1 = copyArray(x);
//			int[] hp1 = copyArray(hp);
//			int ans1 = minAoe1(x1, hp1, range);
//			if (ans1 != ans2) {
//				System.out.println("Oops!");
//				System.out.println(ans1 + "," + ans2);
//			}
            int[] x3 = copyArray(x);
            int[] hp3 = copyArray(hp);
            int ans3 = countAOE(x3, hp3, range);
            if (ans2 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans2 + "," + ans3);
                break;
            }
        }
        System.out.println("test end");
    }
}
