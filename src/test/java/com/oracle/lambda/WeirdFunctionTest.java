package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.functions.Mapper;

public class WeirdFunctionTest {

    @Test
    public void test0() {
        Mapper<Integer,Integer> f = ((Integer)0)::compareTo;
        Mapper<Integer,Integer> f1 = f.compose(f);

        // f1 does what?!
    }

    @Test
    public void test1() {
        Mapper<Integer,Integer> f = ((Integer)0)::compareTo;
        f = f.compose(f);

        Assert.assertEquals(Integer.valueOf(0), f.map(0));
        Assert.assertEquals(Integer.valueOf(1), f.map(100));
        Assert.assertEquals(Integer.valueOf(-1), f.map(-100));
    }

    @Test
    public void test2() {
        Mapper<Integer,Integer> f = ((Mapper<Integer,Integer>)((Integer)0)::compareTo).compose(((Integer)0)::compareTo);
        Assert.assertEquals(Integer.valueOf(0), f.map(0));
        Assert.assertEquals(Integer.valueOf(1), f.map(100));
        Assert.assertEquals(Integer.valueOf(-1), f.map(-100));
    }

}
