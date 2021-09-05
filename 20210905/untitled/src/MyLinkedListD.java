/**
 * Created by flyx
 * Description: 双链表的实现
 * User: 听风
 * Date: 2021-09-05
 * Time: 11:09
 */

class ListNodeD {
    public int val;
    public ListNodeD prev;
    public ListNodeD next;
    public ListNodeD(int val) {
        this.val = val;
    }
}

public class MyLinkedListD {
    private ListNodeD head;
    private ListNodeD last;

    public void addFirst(int val) {
        if (head == null) {
            head = new ListNodeD(val);
            last = head;
        } else {
            head.prev = new ListNodeD(val);
            head.prev.next = head;
            head = head.prev;
        }
    }

    public void addLast(int val) {
        if (last == null) {
            last = new ListNodeD(val);
            head = last;
        } else {
            last.next = new ListNodeD(val);
            last.next.prev = last;
            last = last.next;
        }
    }

    public boolean contains(int val) {
        ListNodeD cur = head;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove(int val) {
        if (head != null && head.val == val) {
            head = head.next;
            return; //删除一次即可
        }
        ListNodeD cur = head == null? null : head.next;
        ListNodeD pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                return;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public void removeAll(int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNodeD cur = head == null? null : head.next;
        ListNodeD pre = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public int getFirst() {
        if (head == null) {
            throw new RuntimeException ("LinkedList is empty.");
        }
        return head.val;
    }

    public int getLast() {
        if (last == null) {
            throw new RuntimeException("LinkedList is empty.");
        }
        return last.val;
    }

    //弹出最后一个元素，并删除
    public int pop() {
        if (head == null) {
            throw new RuntimeException ("LinkedList is empty.");
        }
        ListNodeD res = last;
        last = last.prev;
        if (last == null) {
            head = null;
        } else {
            last.next = null;
        }
        return res.val;
    }

    //弹出第一个元素，并删除
    public int poll() {
        if (head == null) {
            throw new RuntimeException("LinkedList is empty.");
        }
        ListNodeD res = head;
        head = head.next;
        if (head == null) {
            last = null;
        } else {
            head.prev = null;
        }
        return res.val;
    }

}
