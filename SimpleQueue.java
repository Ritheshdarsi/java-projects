import java.util.ArrayList;

public class SimpleQueue {
    ArrayList<Integer> queue = new ArrayList<>();

    public void enqueue(int value) {
        queue.add(value);
    }

    public int dequeue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return queue.remove(0);
    }

    public void display() {
        System.out.println(queue);
    }

    public static void main(String[] args) {
        SimpleQueue q = new SimpleQueue();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();

        q.dequeue();
        q.display();
    }
}
