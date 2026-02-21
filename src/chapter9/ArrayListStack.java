package chapter9;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<T> {

    private final ArrayList<T> data;

    public ArrayListStack() {
        data = new ArrayList<>();
    }

    public void push(T value) {
        data.add(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.remove(data.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}