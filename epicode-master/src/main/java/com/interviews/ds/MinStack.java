package com.interviews.ds;

import java.util.Stack;

public class MinStack {
    long min;
    Stack<Long> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public static void main(String args[]) {

        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(5);
        minStack.push(2);
        minStack.push(4);
        minStack.push(-7);
        minStack.push(6);

        minStack.getMin();
        minStack.pop();

        minStack.getMin();
        minStack.pop();

        minStack.getMin();
        minStack.pop();

        minStack.getMin();
        minStack.pop();

        minStack.getMin();
        minStack.pop();

        minStack.getMin();
        minStack.pop();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = x;
        } else {
            stack.push(x - min);//Could be negative if min value needs to change
            if (x < min) min = x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop = stack.pop();

        if (pop < 0) min = min - pop;//If negative, increase the min value

    }

    public int top() {
        long top = stack.peek();
        if (top > 0) {
            return (int) (top + min);
        } else {
            return (int) (min);
        }
    }

    public int getMin() {
        return (int) min;
    }
}

