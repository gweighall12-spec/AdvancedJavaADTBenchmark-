package chapter9;

import java.util.LinkedList;
import java.util.EmptyStackException;

public class DLinkedListStack<T> {

    private final LinkedList<T> data;

    public DLinkedListStack() {
        data = new LinkedList<>();
    }

    public void push(T value) {
        data.addLast(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.getLast();
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}