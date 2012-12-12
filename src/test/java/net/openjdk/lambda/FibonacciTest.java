package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.IntUnaryOperator;

public class FibonacciTest {

    // Фибоначчийн тоог хүн төрөлхтөн өөрсдийн ахуй (source: Wikipedia MN)
    private IntUnaryOperator fib =
            (n) -> (n < 2) ? n : fib.operateAsInt(n - 1) + fib.operateAsInt(n - 2);

    @Test
    public void test10() {
        Assert.assertEquals(55, fib.operateAsInt(10));
    }

    private static IntUnaryOperator fib_static =
            (n) -> (n < 2) ? n : fib_static.operateAsInt(n - 1) + fib_static.operateAsInt(n - 2);

    @Test
    public void test10_static() {
        Assert.assertEquals(55, fib_static.operateAsInt(10));
    }

//    @Test
//    public void test10_bad() { // should not compile
//        IntUnaryOperator fib =
//                (n) -> (n < 2) ? n : fib.operate(n - 1) + fib.operate(n - 2);
//
//        Assert.assertEquals(55, fib.operateAsInt(10));
//    }

}
