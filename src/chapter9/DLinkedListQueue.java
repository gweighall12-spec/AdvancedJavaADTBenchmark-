package chapter9;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DLinkedListQueue<T> {

    private final LinkedList<T> data;

    public DLinkedListQueue() {
        data = new LinkedList<>();
    }

    public void enqueue(T value) {
        data.addLast(value);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}