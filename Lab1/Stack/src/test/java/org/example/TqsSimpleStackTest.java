package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsSimpleStackTest {
    TqsSimpleStack<String> myStack;

    @BeforeEach
    void setUp() {
        myStack = new TqsSimpleStack<>();
        myStack.push("a");
        myStack.push("b");
        myStack.push("c");
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Push Test")
    @Test
    void push() {
        myStack.push("d");
        assertEquals(4, myStack.size());
        assertFalse(myStack.isEmpty(), "Stack should have at least 1 element");

    }

    @DisplayName("Pop Test")
    @Test
    void pop() {
        assertEquals("c",myStack.pop());
        assertEquals(2,myStack.size());
        myStack = new TqsSimpleStack<>();
        assertThrows(NoSuchElementException.class,()->myStack.pop());
    }

    @DisplayName("Peek Test")
    @Test
    void peek() {
        assertEquals("c",myStack.peek());
        assertEquals(3,myStack.size());
        myStack = new TqsSimpleStack<>();
        assertThrows(NoSuchElementException.class,()->myStack.peek());
    }

    @DisplayName("Size Test")
    @Test
    void size() {
        assertEquals(3,myStack.size());
        myStack = new TqsSimpleStack<>();
        assertEquals(0,myStack.size());
    }

    @DisplayName("IsEmpty Test")
    @Test
    void isEmpty() {
        myStack = new TqsSimpleStack<>();
        assertTrue(myStack.isEmpty(), "Stack should be empty but it's not!");
    }

    @DisplayName("3pop")
    @Test
    void Pop3(){
        myStack.pop();
        myStack.pop();
        myStack.pop();
        assertEquals(0,myStack.size());
        assertTrue(myStack.isEmpty(),"Stack with no elements should be empty");
    }

    @DisplayName("Push Bounded Stack")
    @Test
    void pushBound(){
        myStack = new TqsSimpleStack<>(1);
        myStack.push("a");
        assertThrows(IllegalStateException.class,()->myStack.push("b"));
    }
}