public class CustomFIFO {

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node head;
    public Node tail;

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

    public void getAllItemsFIFO() {
        Node n = head;

        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    public static void main(String[] args) {
        CustomFIFO c = new CustomFIFO();
        c.push(1);
        c.push(2);
        c.push(3);
        c.push(4);
        c.push(5);

        c.getAllItemsFIFO();
    }
}