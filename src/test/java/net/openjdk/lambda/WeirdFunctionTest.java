package net.openjdk.lambda;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class WeirdFunctionTest {

    @Test
    public void test0() {
        Function<Integer,Integer> f = ((Integer)0)::compareTo;
        Function<Integer,Integer> f1 = f.compose(f);

        // f1 does what?!
    }

    @Test
    public void test1() {
        Function<Integer,Integer> f = ((Integer)0)::compareTo;
        f = f.compose(f);

        assertEquals(Integer.valueOf(0), f.apply(0));
        assertEquals(Integer.valueOf(1), f.apply(100));
        assertEquals(Integer.valueOf(-1), f.apply(-100));
    }

    @Test
    public void test2() {
        Function<Integer,Integer> f = ((Function<Integer,Integer>)((Integer)0)::compareTo).compose(((Integer)0)::compareTo);
        assertEquals(Integer.valueOf(0), f.apply(0));
        assertEquals(Integer.valueOf(1), f.apply(100));
        assertEquals(Integer.valueOf(-1), f.apply(-100));
    }

}
