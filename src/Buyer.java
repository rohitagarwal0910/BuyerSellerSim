import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buyer<V> extends BuyerBase<V> {
    public Buyer(int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog,
            int iteration) {
        //  Complete the Buyer Constructor method
        // ...
        setSleepTime(sleepTime);
        this.lock = lock;
        this.full = full;
        this.empty = empty;
        this.catalog = catalog;
        this.setIteration(iteration);
    }

    public void buy() throws InterruptedException {
        lock.lock();
        try {
            //  Complete the try block for consume method
            // ...
            while (catalog.isEmpty()) {
                empty.await();
            }
            NodeBase<V> n = catalog.dequeue();
            full.signalAll();
            System.out.print("Consumed "); // DO NOT REMOVE (For Automated Testing)
            n.show(); // DO NOT REMOVE (For Automated Testing)
            // ...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //  Complete this block
            lock.unlock();
        }
    }
}
