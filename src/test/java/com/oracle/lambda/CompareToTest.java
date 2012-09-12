package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Comparator;

public class CompareToTest {

    @Test
    public void testLegacy() {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? -1 : ((x > y) ? 1 : 0);
            }

        };

        Assert.assertEquals(0, cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1, cmp.compare(100, -100));
    }

    @Test
    public void testLambda() {
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        Assert.assertEquals(0, cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1, cmp.compare(100, -100));
    }

    public void testLambdaEF() {
        int minus_one = -1;
        int zero = 0;
        int one  = 1;

        Comparator<Integer> cmp = (x, y) -> (x < y) ? minus_one : ((x > y) ? one : zero);

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testMethodRef1() {
        Comparator<Integer> cmp = Integer::compare;

        Assert.assertEquals(0, cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1, cmp.compare(100, -100));
    }

    @Test
    public void testMethodRef2() {
        Comparator<Integer> cmp = Integer::compareTo;

        Assert.assertEquals(0, cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1, cmp.compare(100, -100));
    }


}
