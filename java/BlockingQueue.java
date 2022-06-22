package java;

import java.util.*;

/**
 * producter-consumer implementations in java
 */
public class BlockingQueue {
    private final int MAX_SIZE = 5;
    private List<Integer> buffer;

    public BlockingQueue() {
        this.buffer = new ArrayList<>();
    }

    public synchronized void put() {
        while (buffer.size() == MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Random rand = new Random();
        int val = rand.nextInt(100);
        buffer.add(val);
        notify(); // nofityAll();
    }

    public synchronized void take() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.remove(0);
        notify();
    }
}

class Producer implements Runnable {
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.put();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.take();
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue();
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);
        new Thread(p).start();
        Thread.sleep(100);
        new Thread(c).start();
    }
}