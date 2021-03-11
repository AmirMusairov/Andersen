package Collections.Queue;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    static class QueueNode<T> {
        T data;
        QueueNode next;

        QueueNode(T data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return "(" + data + ")";
        }
    }

    private QueueNode<T> head;

    private QueueNode<T> tail;

    void enqueue(T data) {
        QueueNode<T> node = new QueueNode<>(data);
        if (tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = tail;
        }
    }

    T dequeue() {
        if (head == null) throw new NoSuchElementException();
        T data = head.data;

        head = head.next;

        if (head == null) {
            tail = null;
        }
        return data;
    }

    T peek() {
        if (head == null) throw new NoSuchElementException();

        return head.data;
    }

    boolean isEmpty() {
        return head == null;
    }
}
