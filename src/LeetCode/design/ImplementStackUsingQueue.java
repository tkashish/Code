package LeetCode.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kashishtayal on 11/29/16.
 */
public class ImplementStackUsingQueue {
    Queue<Integer> stack;
    public ImplementStackUsingQueue(){
        stack = new LinkedList<>();
    }
    // Push element x onto stack.
    public void push(int x) {
        int size = stack.size();
        stack.add(x);
        for(int i = 0; i < size; i++){
            stack.add(stack.remove());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        stack.remove();
    }

    // Get the top element.
    public int top() {
        return stack.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ImplementStackUsingQueue stack = new ImplementStackUsingQueue();
        System.out.println(stack.empty() == true);
        stack.push(1);
        System.out.println(stack.empty() == false);
        System.out.println(stack.top() == 1);
        stack.push(2);
        System.out.println(stack.top() == 2);
    }
}
