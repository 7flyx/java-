import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyx
 * Description: 模拟实现String类
 * User: 听风
 * Date: 2021-09-20
 * Time: 16:55
 */
public class MyString {
    private StringBuilder root;
    private int length;

    public boolean equals(String str) {
        if (str == null) {
            return false;
        }

        char[] str1 = this.toCharArray();
        char[] str2 = str.toCharArray();
        if (str1.length != str2.length) {
            return false;
        }

        for (int i = 0; i < str1.length; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }

    public int compareTo(String str) {
        if (str == null) {
            throw new RuntimeException("string is null.");
        }

        char[] str1 = this.toCharArray();
        char[] str2 = str.toCharArray();
        int i = 0;
        int j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else {
                return str1[i] - str2[j];
            }
        }

        if (i == str1.length && j == str2.length) {
            return 0;
        }

        return str1.length - str2.length;
    }

    public char[] toCharArray() {
        return root.toString().toCharArray();
    }

    public boolean contains(String str) {
        if (str == null) {
            return false;
        }

        return indexOf(str) != -1;
    }

    public int indexOf(String subString) {
        //KMP算法
        if (subString == null) {
            return -1;
        }

        int[] next = getNextArray(subString);
        char[] str1 = this.toCharArray();
        char[] str2 = subString.toCharArray();
        int i = 0;
        int j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length? i - j : -1;
    }

    private int[] getNextArray(String str) {
        if (str.length() < 2) {
            return new int[] {-1};
        }
        if (str.length() < 3) {
            return new int[] {-1, 0};
        }
        char[] str1 = str.toCharArray();
        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < next.length) {
            if (str1[index - 1] == str1[cn]) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn]; //往前跳
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public int lastIndexOf(String subString) {
        if (subString == null) {
            return -1;
        }
        int[] next = getNextArray(subString);
        char[] str1 = this.toCharArray();
        char[] str2 = subString.toCharArray();
        int i = 0;
        int j = 0;
        int res = -1;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j];
            }
            if (j == str2.length) {
                res = i - j;
                j = j == 1? 0 : next[j - 1]; //防止子串只有一个字符，会回溯到next【0】位置，即-1
            }
        }
        return res;
    }

    public String[] split(String flag) {
        if (flag == null) {
            throw new RuntimeException("flag is null.");
        }
        if (flag.equals("")) {
            return new String[] {root.toString()};
        }

        String[] flags = flag.split("|");
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(); //记录字符串数组的引用
        char[] chars = this.toCharArray();
        int left = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chIsEquals(chars[i], flags)) {
                list.add(this.subString(left, i));
                left = i + 1;
            }
        }
        if (left != this.length) {
            list.add(this.subString(left, this.length));
        }

        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private boolean chIsEquals(char ch, String[] flags) {
        for (int i = 0; i < flags.length; i++) {
            if (flags[i].equals(String.valueOf(ch))) {
                return true;
            }
        }
        return false;
    }

    public String subString(int left, int right) {
        if (left >= this.length || right >= this.length || left < 0) {
            throw new RuntimeException("number is illicit.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = left; i < right; i++) {
            sb.append(root.charAt(i));
        }
        return sb.toString();
    }

    public String subString(int left) {
        if (left >= this.length || left < 0) {
            throw new RuntimeException("number is illicit.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = left; i < this.length; i++) {
            sb.append(root.charAt(i));
        }
        return sb.toString();
    }

    public void trim() {
        int start = 0;
        int end = -1;
        while (root.charAt(start) == ' ') {
            end = start;
            start++;
        }
        if (end != -1) {
            root.delete(0, end + 1);
            this.length -= (start - end);
        }

        end = this.length - 1;
        start = -1;
        while (root.charAt(end) == ' ') {
            start = end;
            end++;
        }
        if (start != -1) {
            root.delete(start + 1, this.length);
            this.length -= (end - start);
        }
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int length() {
        return this.length;
    }
}
