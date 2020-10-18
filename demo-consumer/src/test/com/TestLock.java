import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class TestLock {
    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        new Thread(lockDemo).start();
        new Thread(lockDemo).start();
        new Thread(lockDemo).start();


    }
}

class LockDemo implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    public void run() {
        int sum = 100;

        try {
            while (true) {
                lock.lock();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sum > 0) {
                    System.out.println(--sum);
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
