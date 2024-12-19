package org.example.task1;

public class Lucky {
    static int x = 0;
    static int count = 0;
    private static final Object lock = new Object();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            int current;

            while (true) {
                synchronized (lock){
                    if(x >= 999999){
                        break;
                    }

                    x++;
                    current = x;
                }

                if ((current % 10) + (current / 10) % 10 + (current / 100) % 10 == (current / 1000)
                        % 10 + (current / 10000) % 10 + (current / 100000) % 10) {
                    System.out.println(current);

                    synchronized (lock) {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
