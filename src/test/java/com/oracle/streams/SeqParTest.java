package com.oracle.streams;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SeqParTest {

    private static final int COUNT = Integer.getInteger("count", 1_000_000);

    private List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList<>(COUNT);
        for (int c = 1; c <= COUNT; c++) {
            list.add(c);
        }
        Collections.shuffle(list);
    }

    @Test
    public void testSeq() {
        Optional<Integer> result = list.stream().reduce(Math::max);
        Assert.assertEquals(Integer.valueOf(COUNT), result.get());
    }

    @Test
    public void testPar() {
        Optional<Integer> result = list.parallel().reduce(Math::max);
        Assert.assertEquals(Integer.valueOf(COUNT), result.get());
    }

}
