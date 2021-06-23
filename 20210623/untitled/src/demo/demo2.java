package demo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class demo2 {
    public static void main(String[] args) {
        /*
            贪心算法练习题
            2. 有一些会议需要开，现在只有一间会议室，会议室不能同时进行两个会议，给你这些会议的开始与结束时间
            问 ，怎么安排，这一天开的会议最多，返回最多几次会议的次数
         */


    }

    public static class Meeting {
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.end = end;
            this.start = start;
        }
    }

    public static class meetCompare implements Comparator<Meeting> {
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end; //以结束时间进行排序
        }
    }

    public static int getMaxMeetingNum1 (Meeting[] meet) {
        if (meet == null) {
            return 0;
        }

        //贪心算法
        //以每场会议的结束时间，进行排序
        Arrays.sort(meet, new meetCompare());
        int result = 0;
        int timeLine = 0; //结束时间
        for (int i = 0; i < meet.length; i++) {
            if (timeLine <= meet[i].start) { //上次结束时间小于 下次会议开始时间，就可以安排
                result++;
                timeLine = meet[i].end;
            }
        }
        return result;
    }
}
