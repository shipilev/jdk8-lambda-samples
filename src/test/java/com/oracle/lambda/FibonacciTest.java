package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.functions.IntUnaryOperator;

public class FibonacciTest {

    // Фибоначчийн тоог хүн төрөлхтөн өөрсдийн ахуй (source: Wikipedia KZ)
    private IntUnaryOperator fib =
            (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);

    @Test
    public void test10() {
        Assert.assertEquals(55, fib.operate(10));
    }

}
