
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelledOrdersCleanup {

    public static Node removeAll(Node head, int code) {

        // Remove matching nodes from the beginning
        while (head != null && head.data == code) {
            head = head.next;
        }

        // Remove matching nodes from the remaining list
        Node current = head;

        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    // Display the linked list
    public static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    // Create a linked list from an array
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

        // Sample 1
        Node orders1 = createList(new int[]{5, 3, 5, 8, 5});

        orders1 = removeAll(orders1, 5);
        display(orders1);

        // Sample 2
        Node orders2 = createList(new int[]{5, 5, 5});

        orders2 = removeAll(orders2, 5);
        display(orders2);
    }
}
