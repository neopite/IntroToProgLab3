package IntroToProgLab;

public class Bucket<T> {
    private Node first;
    private Node last;
    private int N;

    public Bucket() {
        first = null;
        last = null;
        N = 0;
    }

    public void add(T item) {
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
        N++;
    }
    public T get(int index){
        Node node=new Node();
        if(index==0){
            return first.data;
        }
        for (int i = 0; i < index; i++) {
            node=first.next;
        }
        return node.data;
    }


    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
        public Node(){

        }
    }

    public static void main(String[] args) {
        Bucket<String> linkedList=new Bucket<>();
        linkedList.add("Hello");
        linkedList.add("World");
        System.out.println(linkedList.get(0));
    }
}

