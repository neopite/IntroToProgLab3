package IntroToProgLab;

public class Bucket<MyEntry> {
    private Node first;
    private Node last;
    private Node prev;
    private int size;

    Bucket() {
        first = null;
        last = null;
        prev = null;
        size = 0;
    }

    void add(MyEntry item) {
        if (item == null) {
            throw new NullPointerException("The first argument for addLast() is null.");
        }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null, prev);
            prev.setNext(last);

        } else {
            last = new Node(item, null, null);
            first = last;
        }
        size++;
    }

    MyEntry get(int index) {
        Node node = new Node();
        node = last;
        for (int i = 0; i < index; i++) {
            node = node.getPrev();
        }
        return node.data;
    }

    class Node {
        MyEntry data;
        Node next;
        Node prev;

        Node(MyEntry data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        Node() {

        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        private Node getPrev() {
            return prev;
        }
    }

    int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;

    }
}


