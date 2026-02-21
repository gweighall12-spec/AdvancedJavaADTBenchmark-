package chapter9;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SortedDLinkedListPriorityQueue<T> {

    private static class Entry<T> {
        int priority;
        T value;

        Entry(int p, T v) {
            priority = p;
            value = v;
        }
    }

    private final LinkedList<Entry<T>> data;

    public SortedDLinkedListPriorityQueue() {
        data = new LinkedList<>();
    }

    public void insert(int priority, T value) {
        Entry<T> entry = new Entry<>(priority, value);

        int index = 0;
        while (index < data.size() && data.get(index).priority <= priority) {
            index++;
        }
        data.add(index, entry);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.removeFirst().value;
    }

    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.getFirst().value;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }
}