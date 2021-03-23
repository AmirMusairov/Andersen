package MultiThreading;

class Counter implements Runnable {
    public static volatile int count = 0;

    public void run() {
        Thread current = Thread.currentThread();
        try {
            while (count < 10) {
                System.out.println(current.getName() + " count = " + count++);
                current.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread one = new Thread(new Counter(), "First Thread");
        Thread two = new Thread(new Counter(), "Second Thread");
        try {
            one.start();
            two.start();
            one.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}