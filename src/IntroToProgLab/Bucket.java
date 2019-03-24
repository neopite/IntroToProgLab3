package IntroToProgLab;

public class Bucket<MyEntry> {
    private Node first;
    private Node last;
    private int size;

    Bucket() {
        first = null;
        last = null;
        size = 0;
    }

    void add(MyEntry item) {
        if (item == null) {
            throw new NullPointerException("The first argument for addLast() is null.");
        }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        } else {
            last = new Node(item, null);
            first = last;
        }
        size++;
    }

    MyEntry get(int index) {
        Node node = new Node();
        if (index == 0) {
            return first.data;
        }
        for (int i = 0; i < index; i++) {
            node = first.next;
        }
        size--;
        return node.data;
    }

    class Node {
        MyEntry data;
        Node next;

        Node(MyEntry data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node() {

        }
    }

    int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;

    }

    public static void main(String[] args) {
        Bucket bucket=new Bucket();
        bucket.add("fe");
        bucket.get(0);
     }

}

