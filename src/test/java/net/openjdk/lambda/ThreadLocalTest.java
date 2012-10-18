package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.functions.Factory;

public class ThreadLocalTest {

    @Test
    public void threadLocalLegacy(){
        final AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return counter.incrementAndGet();
            }
        };
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

    // FIXME: javac is unable to infer ThreadLocal type
    @Test
    public void threadLocalLambda() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>(() -> counter.incrementAndGet());
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

    @Test
    public void threadLocalMRef() {
        AtomicInteger counter = new AtomicInteger(0);
        Factory<Integer> factory = counter::incrementAndGet;
        ThreadLocal<Integer> tlNumber = new ThreadLocal<>(factory);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
        Assert.assertEquals(tlNumber.get(), (Integer)1);
    }

}