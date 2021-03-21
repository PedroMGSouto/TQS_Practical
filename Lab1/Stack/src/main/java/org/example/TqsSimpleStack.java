package org.example;

import java.util.LinkedList;

public class TqsSimpleStack<T>{
    LinkedList<T> stack;
    int limit;

    public TqsSimpleStack() {
        stack = new LinkedList<T>();
        limit = -1;
    }

    public TqsSimpleStack(int limit) {
        stack = new LinkedList<T>();
        this.limit = limit;
    }

    public void push(T x){
        if(limit!=-1 && stack.size()>=limit){
            throw new IllegalStateException();
        }
        else{
            stack.add(x);
        }
    }

    public T pop(){
        return stack.removeLast();
    }

    public T peek(){
        return stack.getLast();
    }

    public int size(){
        return stack.size();
    }
    
    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
