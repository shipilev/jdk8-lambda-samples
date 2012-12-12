package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.function.Function;

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

        Assert.assertEquals(Integer.valueOf(0), f.apply(0));
        Assert.assertEquals(Integer.valueOf(1), f.apply(100));
        Assert.assertEquals(Integer.valueOf(-1), f.apply(-100));
    }

    @Test
    public void test2() {
        Function<Integer,Integer> f = ((Function<Integer,Integer>)((Integer)0)::compareTo).compose(((Integer)0)::compareTo);
        Assert.assertEquals(Integer.valueOf(0), f.apply(0));
        Assert.assertEquals(Integer.valueOf(1), f.apply(100));
        Assert.assertEquals(Integer.valueOf(-1), f.apply(-100));
    }

}
