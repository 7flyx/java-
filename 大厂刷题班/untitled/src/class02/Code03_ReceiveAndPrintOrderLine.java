package class02;

import java.util.HashMap;

/**
 * Created by Terry
 * User: Administrator
 * Date: 2023-02-16
 * Time: 18:50
 * Description:
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 */
public class Code03_ReceiveAndPrintOrderLine {
    public static void main(String[] args) {
        MessageBox box = new MessageBox();
        box.receive(3, "c");
        box.receive(2, "b");
        box.receive(5, "e");
        box.receive(1, "a");

        box.receive(6, "f");
        box.receive(8, "h");
        box.receive(7, "g");
        box.receive(4, "d");

        box.receive(9, "i");
        box.receive(10, "j");
    }

    public static class MessageBox {
        public HashMap<Integer, Node> headMap; //存储消息的头部
        public HashMap<Integer, Node> tailMap; //存储消息的尾部
        public int waitPoint; // 当前等待的消息

        private static class Node { // 单链表节点
            public int num;
            public String message;
            public Node next;

            public Node(int num, String message) {
                this.num = num;
                this.message = message;
            }
        }

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String message) {
            Node node = new Node(num, message);
            if (tailMap.containsKey(num - 1)) { // 上一个节点存在(尾巴)
                tailMap.get(num - 1).next = node;
                tailMap.remove(num - 1);
            } else { // 往上连不上，说明这是新的头部
                headMap.put(num, node); // 新的头部
            }
            if (headMap.containsKey(num + 1)) { // 下一个节点存在（头部）
                node.next = headMap.get(num + 1);
                headMap.remove(num + 1);
            } else { // 往下连不上，说明这是新的尾部
                tailMap.put(num, node); // 新的尾巴
            }

            if (num == waitPoint) { // 当前想打印的节点，已经存在了，就打印
                print(num);
            }
        }

        private void print(int num) {
            Node node = headMap.get(num);
            headMap.remove(num); // 删除头节点在map中的存储
            while (node != null) {
                System.out.print(node.message + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1); // 删除尾结点在map中的存储
            System.out.println();
        }
    }
}
