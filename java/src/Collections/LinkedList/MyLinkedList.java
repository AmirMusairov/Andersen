package Collections.LinkedList;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    private int size;

    public MyLinkedList() {
    }

    public void addFirst(T t) {
        Node<T> node = new Node<>(t);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public T getFirst() {
        if (this.head == null) return null;
        return this.head.data;
    }

    public void addLast(T t) {
        Node<T> node = new Node<>(t);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T getLast() {
        if (head == null) return null;
        return tail.data;
    }

    public T get(int index) {
        if (head == null) return null;

        Node<T> node = head;
        int counter = 0;
        while (node != null) {
            if (counter == index) return node.data;
            node = node.next;
            counter++;
        }

        return null;
    }

    public boolean insert(int index, T t) {
        if (head == null) return false;

        Node<T> newNode = new Node<>(t);

        Node<T> node = head;
        Node<T> prev = head;

        int counter = 0;
        while (node != null) {
            if (counter == index) {
                newNode.next = node;
                prev.next = newNode;
                size++;
                return true;
            }
            prev = node;
            node = node.next;
            counter++;
        }
        return false;
    }

    public void remove(int index) {
        if (head == null) return;

        Node<T> prev = head;
        Node<T> node = head;

        int counter = 0;

        while (node != null) {
            if (counter == index) {
                prev.next = node.next;
                size--;
            }
            counter++;
            prev = node;
            node = node.next;
        }
    }

    public int size() {
        return size;
    }

    private class Node<T> {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "(" + data + ")";
        }
    }
}
