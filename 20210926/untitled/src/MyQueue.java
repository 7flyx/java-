/**
 * Created by IDEA
 * User: 听风
 * Date: 2021-09-26
 * Time: 20:54
 * Description:
 */
class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}

public class MyQueue {

    private Node front;
    private Node rear;
    private int usedSize;

    /**
     * 入队列
     * @param val 值
     */
    public void offer(int val) {
        if (this.rear == null) {
            this.rear = new Node(val);
            this.front = this.rear;
        } else {
            Node node = new Node(val);
            this.rear.next = node;
            this.rear = node;
        }
        this.usedSize++;
    }

    /**
     * 出队头元素
     * @return
     */
    public int poll() {
        return -1;
    }

    /**
     * 得到队头元素 但是不删除
     */
    public void peek() {

    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return -1;
    }




}
