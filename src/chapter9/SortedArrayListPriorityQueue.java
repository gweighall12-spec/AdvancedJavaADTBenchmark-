package chapter9;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SortedArrayListPriorityQueue<T> {

    private static class Entry<T> {
        int priority;
        T value;

        Entry(int p, T v) {
            priority = p;
            value = v;
        }
    }

    private final ArrayList<Entry<T>> data;

    public SortedArrayListPriorityQueue() {
        data = new ArrayList<>();
    }

    public void insert(int priority, T value) {
        Entry<T> entry = new Entry<>(priority, value);

        int i = 0;
        while (i < data.size() && data.get(i).priority <= priority) {
            i++;
        }
        data.add(i, entry);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.remove(0).value;
    }

    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(0).value;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }
}