public class CountNodes {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static int countNodes(Node head) {

        int count = 0;

        Node current = head;

        while (current != null) {

            count++;

            current = current.next;
        }

        return count;
    }

    public static void main(String[] args) {

        Node head = new Node(5);
        head.next = new Node(10);
        head.next.next = new Node(15);

        int result = countNodes(head);

        System.out.println("Total Nodes: " + result);
    }
}
