import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        String lock = new String();

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        pool.schedule(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("ABC");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 1, TimeUnit.SECONDS);

        pool.schedule(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("ABC2");
                    lock.notify();
                }
            }
        },3, TimeUnit.SECONDS);

        pool.shutdown();
    }
}
