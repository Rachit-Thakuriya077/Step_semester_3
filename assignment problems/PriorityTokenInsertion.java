
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class PriorityTokenInsertion {
    Node head;

    // Insert at the beginning
    public void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    // Insert at the end
    public void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Insert at a specified 0-based index
    public void insertAt(int index, int token) {
        if (index < 0) {
            System.out.println("Invalid index");
            return;
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node temp = head;

        // Reach the node just before the index
        for (int i = 0; i < index - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid index");
            return;
        }

        Node newNode = new Node(token);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Display the list
    public void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        PriorityTokenInsertion list = new PriorityTokenInsertion();

        // Starting list: 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.display();
    }
}

