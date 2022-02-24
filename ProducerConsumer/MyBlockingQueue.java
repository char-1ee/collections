package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyBlockingQueue {
    private final int MAX_SIZE = 5;
    private List<Integer> buffer;

    public MyBlockingQueue() {
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
    private MyBlockingQueue queue;
    public Producer (MyBlockingQueue queue) {
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
    private MyBlockingQueue queue;
    public Consumer (MyBlockingQueue queue) {
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
        MyBlockingQueue queue = new MyBlockingQueue();
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);
        new Thread(p).start();
        Thread.sleep(100);
        new Thread(c).start();
    }
}