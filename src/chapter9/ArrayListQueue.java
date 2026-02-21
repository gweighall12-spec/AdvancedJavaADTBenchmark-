package chapter9;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayListQueue<T> {

    private ArrayList<T> buffer;
    private int head;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    public ArrayListQueue() {
        buffer = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buffer.add(null);
        }
        head = 0;
        size = 0;
    }

    public void enqueue(T value) {
        ensureCapacity();
        int tail = (head + size) % buffer.size();
        buffer.set(tail, value);
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = buffer.get(head);
        buffer.set(head, null);
        head = (head + 1) % buffer.size();
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return buffer.get(head);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == buffer.size()) {
            int newCap = buffer.size() * 2;
            ArrayList<T> newBuffer = new ArrayList<>(newCap);
            for (int i = 0; i < newCap; i++) {
                newBuffer.add(null);
            }

            for (int i = 0; i < size; i++) {
                newBuffer.set(i, buffer.get((head + i) % buffer.size()));
            }

            buffer = newBuffer;
            head = 0;
        }
    }
}