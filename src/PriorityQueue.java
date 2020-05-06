
public class PriorityQueue<V> implements QueueInterface<V> {

    private NodeBase<V>[] queue;
    private int capacity, currentSize, front, rear;

    //  Complete the Priority Queue implementation
    // You may create other member variables/ methods if required.
    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        currentSize = 0;
        front = 0;
        rear = 0;
        queue = new NodeBase[this.capacity];
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    public void enqueue(Node<V> node) {
        if (!isFull()) {
            currentSize++;
            queue[rear++ % capacity] = node;
        }
    }
    // In case of priority queue, the dequeue() should
    // always remove the element with minimum priority value
    public NodeBase<V> dequeue() {
        if (!isEmpty()) {
            int hp = queue[front % capacity].getPriority();
            int dq = front % capacity;
            for (int i = front; i < front + currentSize; i++) {
                if (queue[i % capacity].getPriority() < hp) {
                    hp = queue[i % capacity].getPriority();
                    dq = i % capacity;
                }
            }
            NodeBase<V> temp = queue[dq % capacity];
            queue[dq % capacity] = queue[front % capacity];
            queue[front % capacity] = temp;
            currentSize--;
            return queue[front++ % capacity];
        } else {
            return null;
        }
    }

    public void display() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty");
        }
        for (int i = front; i < front+currentSize; i++) {
            queue[i % capacity].show();
        }
    }
}
