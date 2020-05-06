// This class implements the Queue
public class Queue<V> implements QueueInterface<V> {

    // Complete the Queue implementation
    private NodeBase<V>[] queue;
    private int capacity, currentSize, front, rear;

    public Queue(int capacity) {
        front = 0;
        rear = 0;
        currentSize = 0;
        this.capacity = capacity;
        queue = new NodeBase[capacity];
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

    public NodeBase<V> dequeue() {
        if (!isEmpty()) {
            currentSize--;
            return queue[front++ % capacity];
        } else
            return null;
    }

}
