/*
head
 ↓
[ 1 | next ] → [ 2 | next ] → [ 3 | next ] → [ 4 | next ] → [ 5 | next:null ]
                                                              ↑
                                                            tail
*/
public class FIFOCustom {

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node head;
    public Node tail;

    /*Also called enqueue Add element to the rear Must be O(1)*/
    public void push(int i) {
        Node n = new Node(i);

        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    public void pop(){
        tail = tail.next;
    }

    public void traverse() {
        Node n = head;

        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    public static void main(String[] args) {
        FIFOCustom c = new FIFOCustom();
        c.push(1);
        c.push(2);
        c.push(3);
        c.push(4);
        c.push(5);
        System.out.println(c.tail.next);
        c.traverse();
    }
}