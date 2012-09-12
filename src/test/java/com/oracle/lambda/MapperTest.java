package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.functions.Mapper;

public class MapperTest {

    @Test
    public void parseInt(){
        Mapper<String, Integer> f = Integer::parseInt;
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    @Test
    public void constructor(){
        Mapper<String, Integer> f = Integer::new;
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

}