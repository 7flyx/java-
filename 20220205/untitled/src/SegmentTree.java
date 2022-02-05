/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-02-05
 * Time: 18:14
 * Description:线段树
 */
public class SegmentTree {
    private int length;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public SegmentTree(int[] arr) {
        this.length = arr.length + 1;
        this.arr = new int[length];
        for (int i = 1; i < length; i++) {
            this.arr[i] = arr[i - 1];
        }

        this.lazy = new int[length << 2];
        this.sum = new int[length << 2];
        this.change = new int[length << 2];
        this.update = new boolean[length << 2];
    }

    private void pushUp(int rt) {
        this.sum[rt] = this.sum[rt << 1] + this.sum[rt << 1 | 1];
    }

    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            sum[rt << 1] = ln * change[rt];
            sum[rt << 1 | 1] = rn * change[rt];
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1] += ln * lazy[rt];
            sum[rt << 1 | 1] += rn * lazy[rt];
            lazy[rt] = 0;
        }
    }

    public void build(int curL, int curR, int rt) {
        if (curL == curR) {
            sum[rt] = arr[curL];
            return;
        }
        int mid = (curL + curR) >> 1;
        build(curL, mid, rt << 1); //左子树
        build(mid + 1, curR, rt << 1 | 1); //右子树
        pushUp(rt); //汇总
    }

    public void add(int L, int R, int val, int curL, int curR, int rt) {
        if (L <= curL && R >= curR) {
            lazy[rt] = val;
            sum[rt] += (curR - curL + 1) * val;
            return;
        }
        int mid = (curL + curR) >> 1; //取中间值
        pushDown(rt, mid - curL + 1, curR - mid);//往下分发
        if (L <= mid) {
            add(L, R, val, curL, mid, rt << 1); //递归左子树
        }
        if (R > mid) {
            add(L, R, val, mid + 1, curR, rt << 1 | 1);//递归右子树
        }
        pushUp(rt); //将左右孩子汇总起来
    }

    public void update(int L, int R, int val, int curL, int curR, int rt) {
        if (L <= curL && R >= curR) {
            update[rt] = true;
            change[rt] = val;
            sum[rt] = (curR - curL + 1) * val;
            return;
        }

        int mid = (curL + curR) >> 1;
        pushDown(rt, mid - curL + 1, curR - mid);
        if (L <= mid) {
            update(L, R, val, curL, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, val, mid + 1, curR, rt << 1 | 1);
        }
        pushUp(rt); //汇总
    }

    public long query(int L, int R, int curL, int curR, int rt) {
        if (L <= curL && R >= curR) {
            return sum[rt];
        }
        long res = 0;
        int mid = (curL + curR) >> 1;
        if (L <= mid) {
            res += query(L, R, curL, mid, rt << 1);
        }
        if (R > mid) {
            res += query(L, R, mid + 1, curR, rt << 1 | 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree seg = new SegmentTree(arr);

        seg.build(1, 10, 1);
        System.out.println(seg.query(2, 4, 1, 10, 1));
        seg.update(2, 4, 1, 1, 10, 1);
        System.out.println(seg.query(2, 4, 1, 10, 1));
        seg.add(2, 5, 2, 1, 10, 1);
        System.out.println(seg.query(2, 5, 1, 10, 1));
    }
}
