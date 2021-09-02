/**
 * Created by flyx
 * Description: 双链表的实现
 * User: 听风
 * Date: 2021-09-02
 * Time: 20:39
 */

class ListNodeD {
    public int val;
    public ListNodeD prev, next;

    public ListNodeD(int val) {
        this.val = val;
    }
}

public class MyLinkedList {
    private ListNodeD head;
    private ListNodeD last;

    //头插法
    public void addFirst(int data) {
        if (head == null) {
            head = new ListNodeD(data);
            last = head;
        } else {
            ListNodeD node = new ListNodeD(data);
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
    //尾插法
    public void addLast(int data) {
        if (last == null) {
            last = new ListNodeD(data);
            head = last;
        } else {
            ListNodeD node = new ListNodeD(data);
            node.prev = last;
            last.next = node;
            last = node;
        }
    }
    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data) {
        if (index < 0 || index > size()) {
            throw new RuntimeException("index不合法！");
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == size()) {
            addLast(data);
        } else {
            ListNodeD cur = head;
            ListNodeD node = new ListNodeD(data);
            while (index-- > 0) {
                cur = cur.next;
            }
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
        }
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key) {
        ListNodeD cur = head;
        while (cur != null) {
            if (cur.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        //分为三种情况，删头，删尾，删中间节点
        ListNodeD cur = head;
        while (cur != null) {
            if (cur.val == key) {
                if (cur == head) { //头
                    head = head.next;
                    if (head == null) {
                        last = null;
                    } else {
                        head.prev = null;
                    }
                    break;
                } else { //中间节点或者是尾节点
                    cur.prev.next = cur.next;
                    if (cur.next == null) {
                        last = cur.prev;
                    } else {
                        cur.next.prev = cur.prev;
                    }
                    break;
                }
            } else {
                cur = cur.next;
            }
        }
    }
    //删除所有值为key的节点
    public void removeAllKey(int key) {
        ListNodeD cur = head;
        while (cur != null) {
            if (cur.val == key) {
                if (cur == head) {
                    head = cur.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        last = null;
                        return;
                    }
                } else {
                    cur.prev.next = cur.next;
                    if (cur.next == null) {
                        last = cur.prev;
                    } else {
                        cur.next.prev = cur.prev;
                    }
                }
            } else {
                cur = cur.next;
            }
        }
    }
    //得到单链表的长度
    public int size() {
        ListNodeD cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    public void display() {
        ListNodeD cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public void clear() {
        //不能像单链表那样直接将head、last赋值为null
        //链表内部还是相连的
        ListNodeD cur = head;
        while (cur != null) {
            ListNodeD next = cur.next;
            cur.prev = null;
            cur.next = null;
            cur = next;
        }
        head = null;
        last = null;
    }
}




