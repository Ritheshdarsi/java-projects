import java.util.ArrayList;

public class StackUsingArrayList {
    private ArrayList<Integer> elements;

    public StackUsingArrayList() {
        elements = new ArrayList<>();
    }

    public void push(int value) {
        elements.add(value);
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Cannot pop: Stack is empty");
            return -1;
        }
        return elements.remove(elements.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void printStack() {
        System.out.println("Stack: " + elements);
    }

    public static void main(String[] args) {
        StackUsingArrayList stack = new StackUsingArrayList();
        stack.push(5);
        stack.push(15);
        stack.push(25);

        stack.printStack();

        System.out.println("Top: " + stack.peek());

        System.out.println("Popped: " + stack.pop());

        stack.printStack();

        System.out.println("Is empty? " + stack.isEmpty());
    }
}
