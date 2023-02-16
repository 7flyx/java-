package class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2022-12-30
 * Time: 16:10
 * Description: 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 */
public class Code02_CountFiles {
    public static void main(String[] args) {
        String path = "E:\\Github_Coding\\Algorithm-of-test-library\\体系学习班\\untitled\\src";
        System.out.println(countFiles1(path));
    }

    // 广度搜索
    public static int countFiles1(String path) {
        File file = new File(path);
        int ans = 0;
        Queue<File> queue = new LinkedList<>();
        queue.add(file);
        while (!queue.isEmpty()) {
            File tmp = queue.poll();
            for (File next : tmp.listFiles()) {
                if (next.isFile()) {
                    ans++;
                } else if (next.isDirectory()) {
                    queue.add(next);
                }
            }
        }
        return ans;
    }

    // 深度优先遍历
    public static int countFiles2(String path) {
        File file = new File(path);
        int ans = 0;
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while (!stack.isEmpty()) {
            File tmp = stack.pop();
            for (File next : tmp.listFiles()) {
                if (next.isDirectory()) {
                    stack.push(next);
                } else if (next.isFile()) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
