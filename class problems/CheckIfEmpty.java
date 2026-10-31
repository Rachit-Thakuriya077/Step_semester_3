public class CheckIfEmpty {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static boolean checkIfEmpty(Node head) {

        return head == null;
    }

    public static void main(String[] args) {

        Node head = null;

        if (checkIfEmpty(head)) {
            System.out.println("List is Empty");
        } else {
            System.out.println("List is Not Empty");
        }
    }
}