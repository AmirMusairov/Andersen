package MultiThreading;

import lombok.ToString;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ToString
public class ThreadPool implements Runnable {
    private String message;

    public ThreadPool(String s) {
        this.message = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " (Start) message = " + message);
        processMessage();
        System.out.println(Thread.currentThread().getName() + " (End)");
    }

    private void processMessage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable obj = new ThreadPool("" + i);
            executor.execute(obj);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("======All threads are Finished=====");
    }
}