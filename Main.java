import java.util.Stack;

class MinMaxStack {
    private Stack<int[]> stack;

    public MinMaxStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new int[]{val, val, val});
        } else {
            int currentMin = stack.peek()[1];
            int currentMax = stack.peek()[2];
            stack.push(new int[]{val, Math.min(val, currentMin), Math.max(val, currentMax)});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

    public int getMax() {
        return stack.peek()[2];
    }
}

public class Main {
    public static void main(String[] args) {
        MinMaxStack stack = new MinMaxStack();

        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.push(1);
        System.out.println("Top: " + stack.top());     
        System.out.println("Min: " + stack.getMin());  
        System.out.println("Max: " + stack.getMax());  
        stack.pop();
        System.out.println("After pop:");
        System.out.println("Top: " + stack.top());     
        System.out.println("Min: " + stack.getMin());  
        System.out.println("Max: " + stack.getMax());  
    }
}
