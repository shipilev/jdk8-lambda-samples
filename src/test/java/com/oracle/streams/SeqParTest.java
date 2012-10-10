package com.oracle.streams;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeqParTest {

    private static final int COUNT = Integer.getInteger("count", 10000);

    private List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList<>(COUNT);
        for (int c = 1; c <= COUNT; c++) {
            list.add(c);
        }
    }

    @Test
    public void testSeq() {
        Optional<Integer> result = list.stream().reduce((l, r) -> l + r);
        Assert.assertEquals(Integer.valueOf(50005000), result.get());
    }

    @Test
    public void testPar() {
        Optional<Integer> result = list.parallel().reduce((l, r) -> l + r);
        Assert.assertEquals(Integer.valueOf(50005000), result.get());
    }

}
