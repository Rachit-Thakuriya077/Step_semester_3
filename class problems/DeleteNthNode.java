public class DeleteNthNode {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node deleteNthNode(Node head, int n) {

        if (n == 1) {
            return head.next;
        }

        Node current = head;

        for (int i = 1; i < n - 1; i++) {
            current = current.next;
        }

        current.next = current.next.next;

        return head;
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

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        int n = 3;

        head = deleteNthNode(head, n);

        display(head);
    }
}