package net.openjdk.lambda;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ThreadLocalTest {

    @Test
    public void threadLocalLegacy(){
        final AtomicInteger counter = new AtomicInteger(0); //final isn't necessary
        ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return counter.incrementAndGet();
            }
        };
        assertEquals(tlNumber.get(), (Integer)1);
        assertEquals(tlNumber.get(), (Integer)1);
    }

    @Test
    public void threadLocalLambda() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = ThreadLocal.withInitial(() -> counter.incrementAndGet());
        assertEquals(tlNumber.get(), (Integer)1);
        assertEquals(tlNumber.get(), (Integer)1);
    }

    @Test
    public void threadLocalMRef() {
        AtomicInteger counter = new AtomicInteger(0);
        ThreadLocal<Integer> tlNumber = ThreadLocal.withInitial(counter::incrementAndGet);
        assertEquals(tlNumber.get(), (Integer)1);
        assertEquals(tlNumber.get(), (Integer)1);
    }

}
