package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.functions.IntUnaryOperator;

public class FibonacciTest {

    // Фибоначчийн тоог хүн төрөлхтөн өөрсдийн ахуй (source: Wikipedia MN)
    private IntUnaryOperator fib =
            (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);

    @Test
    public void test10() {
        Assert.assertEquals(55, fib.operate(10));
    }

    private static IntUnaryOperator fib_static =
            (n) -> (n < 2) ? n : fib_static.operate(n - 1) + fib_static.operate(n - 2);

    @Test
    public void test10_static() {
        Assert.assertEquals(55, fib.operate(10));
    }

//    @Test
//    public void test10_bad() { // should not compile
//        IntUnaryOperator fib =
//                (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);
//
//        Assert.assertEquals(55, fib.operate(10));
//    }


}
