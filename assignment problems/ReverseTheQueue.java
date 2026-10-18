
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ReverseTheQueue {

    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;  // Save next node
            current.next = previous;   // Reverse the link
            previous = current;        // Move previous forward
            current = next;            // Move current forward
        }

        return previous;
    }

    public static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static Node createList(int[] values) {
        Node head = null;
        Node tail = null;

        for (int value : values) {
            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Node queue1 = createList(new int[]{1, 2, 3, 4});
        queue1 = reverse(queue1);
        display(queue1);

        Node queue2 = createList(new int[]{9});
        queue2 = reverse(queue2);
        display(queue2);
    }
}

