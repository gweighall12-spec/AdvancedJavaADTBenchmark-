package benchmark;

import chapter9.*;

import java.util.*;

public class BenchmarkDriver {

    private static final int WARMUP_OPS = 15_000;
    private static final int MEASURE_OPS = 60_000;
    private static final int TRIALS = 7;
    private static final long SEED = 12345L;

    public static void main(String[] args) {

        System.out.println("===== STACKS =====");
        benchmarkStack("ArrayListStack", new ArrayListStack<>());
        benchmarkStack("DLinkedListStack", new DLinkedListStack<>());

        System.out.println("\n===== QUEUES =====");
        benchmarkQueue("ArrayListQueue", new ArrayListQueue<>());
        benchmarkQueue("DLinkedListQueue", new DLinkedListQueue<>());

        System.out.println("\n===== PRIORITY QUEUES (Sorted) =====");
        benchmarkPriorityQueue("SortedArrayListPQ", new SortedArrayListPriorityQueue<>());
        benchmarkPriorityQueue("SortedDLinkedListPQ", new SortedDLinkedListPriorityQueue<>());
    }

    /* ================= STACK ================= */

    private static void benchmarkStack(String name, Object stackObj) {

        List<Long> times = new ArrayList<>();

        for (int t = 0; t < TRIALS; t++) {

            Random rand = new Random(SEED);

            // Warmup
            for (int i = 0; i < WARMUP_OPS; i++) {
                pushStack(stackObj, rand.nextInt());
            }
            for (int i = 0; i < WARMUP_OPS; i++) {
                popStack(stackObj);
            }

            long checksum = 0;

            long start = System.nanoTime();

            for (int i = 0; i < MEASURE_OPS; i++) {
                pushStack(stackObj, rand.nextInt());
            }

            for (int i = 0; i < MEASURE_OPS; i++) {
                checksum += (Integer) popStack(stackObj);
            }

            long end = System.nanoTime();
            times.add(end - start);

            System.out.println(name + " Trial " + t + " checksum = " + checksum);
        }

        printMedian(name, times);
    }

    /* ================= QUEUE ================= */

    private static void benchmarkQueue(String name, Object queueObj) {

        List<Long> times = new ArrayList<>();

        for (int t = 0; t < TRIALS; t++) {

            Random rand = new Random(SEED);

            for (int i = 0; i < WARMUP_OPS; i++) {
                enqueue(queueObj, rand.nextInt());
            }
            for (int i = 0; i < WARMUP_OPS; i++) {
                dequeue(queueObj);
            }

            long checksum = 0;

            long start = System.nanoTime();

            for (int i = 0; i < MEASURE_OPS; i++) {
                enqueue(queueObj, rand.nextInt());
            }

            for (int i = 0; i < MEASURE_OPS; i++) {
                checksum += (Integer) dequeue(queueObj);
            }

            long end = System.nanoTime();
            times.add(end - start);

            System.out.println(name + " Trial " + t + " checksum = " + checksum);
        }

        printMedian(name, times);
    }

    /* ================= PRIORITY QUEUE ================= */

    private static void benchmarkPriorityQueue(String name, Object pqObj) {

        List<Long> times = new ArrayList<>();

        for (int t = 0; t < TRIALS; t++) {

            Random rand = new Random(SEED);

            // Warmup
            for (int i = 0; i < WARMUP_OPS; i++) {
                insertPQ(pqObj, rand.nextInt(100), rand.nextInt());
            }
            for (int i = 0; i < WARMUP_OPS; i++) {
                removePQ(pqObj);
            }

            long checksum = 0;

            long start = System.nanoTime();

            for (int i = 0; i < MEASURE_OPS; i++) {
                insertPQ(pqObj, rand.nextInt(100), rand.nextInt());
            }

            for (int i = 0; i < MEASURE_OPS; i++) {
                checksum += (Integer) removePQ(pqObj);
            }

            long end = System.nanoTime();
            times.add(end - start);

            System.out.println(name + " Trial " + t + " checksum = " + checksum);
        }

        printMedian(name, times);
    }

    /* ================= HELPERS ================= */

    private static void printMedian(String name, List<Long> times) {
        Collections.sort(times);
        long median = times.get(times.size() / 2);
        double nsPerOp = (double) median / (2 * MEASURE_OPS);
        System.out.println(name + " MEDIAN: " + nsPerOp + " ns/op");
    }

    private static void pushStack(Object s, int val) {
        if (s instanceof ArrayListStack)
            ((ArrayListStack<Integer>) s).push(val);
        else
            ((DLinkedListStack<Integer>) s).push(val);
    }

    private static Object popStack(Object s) {
        if (s instanceof ArrayListStack)
            return ((ArrayListStack<Integer>) s).pop();
        else
            return ((DLinkedListStack<Integer>) s).pop();
    }

    private static void enqueue(Object q, int val) {
        if (q instanceof ArrayListQueue)
            ((ArrayListQueue<Integer>) q).enqueue(val);
        else
            ((DLinkedListQueue<Integer>) q).enqueue(val);
    }

    private static Object dequeue(Object q) {
        if (q instanceof ArrayListQueue)
            return ((ArrayListQueue<Integer>) q).dequeue();
        else
            return ((DLinkedListQueue<Integer>) q).dequeue();
    }

    private static void insertPQ(Object pq, int priority, int val) {
        if (pq instanceof SortedArrayListPriorityQueue)
            ((SortedArrayListPriorityQueue<Integer>) pq).insert(priority, val);
        else
            ((SortedDLinkedListPriorityQueue<Integer>) pq).insert(priority, val);
    }

    private static Object removePQ(Object pq) {
        if (pq instanceof SortedArrayListPriorityQueue)
            return ((SortedArrayListPriorityQueue<Integer>) pq).removeMin();
        else
            return ((SortedDLinkedListPriorityQueue<Integer>) pq).removeMin();
    }
}