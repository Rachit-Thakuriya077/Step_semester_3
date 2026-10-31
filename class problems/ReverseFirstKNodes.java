public class ReverseFirstKNodes {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverseFirstKNodes(Node head, int k) {

        Node prev = null;
        Node current = head;
        Node next = null;

        for (int i = 0; i < k; i++) {

            next = current.next;

            current.next = prev;

            prev = current;

            current = next;
        }

        head.next = current;

        return prev;
    }

    static void display(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int k = 3;

        head = reverseFirstKNodes(head, k);

        display(head);
    }
}
