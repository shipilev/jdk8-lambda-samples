package net.openjdk.lambda;

import org.junit.Test;

import java.util.function.IntUnaryOperator;

import static org.junit.Assert.*;

public class FibonacciTest {

    // Фибоначчийн тоог хүн төрөлхтөн өөрсдийн ахуй (source: Wikipedia MN)
    private IntUnaryOperator fib;
    {fib = (n) -> (n < 2) ? n : fib.applyAsInt(n - 1) + fib.applyAsInt(n - 2);}

    @Test
    public void test10() {
        assertEquals(55, fib.applyAsInt(10));
    }

    private static IntUnaryOperator fib_static;
    static {fib_static = (n) -> (n < 2) ? n : fib_static.applyAsInt(n - 1) + fib_static.applyAsInt(n - 2);}

    @Test
    public void test10_static() {
        assertEquals(55, fib_static.applyAsInt(10));
    }

//    @Test
//    public void test10_bad() { // should not compile
//        IntUnaryOperator fib =
//                (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);
//
//        assertEquals(55, fib.operateAsInt(10));
//    }

}
