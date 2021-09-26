/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-26
 * Time: 10:54
 * Description: 实现LinkedList
 */
public class MyLinkedList {
    private Node first, last; //头尾
    private int size;

    private static class Node {
        public int val;
        public Node prev, next;

        public Node(int val) {
            this.val = val;
        }
    }

    public void addFirst(int val) {
        if (this.first == null) {
            this.first = new Node(val);
            this.last = this.first;
        } else {
            Node node = new Node(val);
            node.next = this.first;
            this.first.prev = node;
            this.first = node;
        }
        this.size++;
    }

    public void addLast(int val) {
        if (this.first == null) {
            this.first = new Node(val);
            this.last = this.first;
        } else {
            Node node = new Node(val);
            node.prev = this.last;
            this.last.next = node;
            this.last = node;
        }
        this.size++;
    }

    public void addOfIndex(int val, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("index is illegal.");
        }

        if (index == 0) { //头插
            addFirst(val);
        } else if (index == size) { //尾插
            addLast(val);
        } else { //插中间
            Node cur = this.first;
            Node node = new Node(val);
            while (index-- > 0) {
                cur = cur.next;
            }
            node.next = cur;
            node.prev = cur.prev;
            cur.prev = node;
            node.prev.next = node;
            size++;
        }
    }

    public boolean contains(int val) {
        Node cur = this.first;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int searchOfIndex(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index is illegal.");
        }

        Node cur = this.first;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void delOfIndex(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index is illegal.");
        }

        if (index == 0) {
            this.first = this.first.next;
            if (this.first != null) {
                this.first.prev = null;
            } else {
                this.last = null;
            }
        } else if (index == size - 1) {
            this.last = this.last.prev;
            if (this.last != null) {
                this.last.next = null;
            } else {
                this.first = null;
            }
        } else {
            Node cur = this.first;
            while (index-- > 0) {
                cur = cur.next;
            }
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
        this.size--;
    }

    public int modifyNumOfIndex(int newNum, int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("index is illegal.");
        }

        Node cur = this.first;
        while (index-- > 0) {
            cur = cur.next;
        }

        int oldNum = cur.val;
        cur.val = newNum;
        return oldNum; //将旧的数据返回去
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

}
