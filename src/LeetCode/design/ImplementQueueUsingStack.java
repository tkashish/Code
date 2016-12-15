package LeetCode.design;

import java.util.Stack;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class ImplementQueueUsingStack {
    Stack<Integer> stack = new Stack<>();
    // Push element x to the back of queue.
    public void push(int x) {
        Stack<Integer> helper = new Stack<>();
        while(!stack.isEmpty()){
            helper.push(stack.pop());
        }
        stack.push(x);
        while(!helper.isEmpty()){
            stack.push(helper.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        stack.pop();
    }

    // Get the front element.
    public int peek() {
        return stack.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack.isEmpty();
    }
}
