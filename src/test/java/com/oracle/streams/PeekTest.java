package com.oracle.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.streams.Stream;

public class PeekTest {

    @Test
    public void testPeek() {
        Stream<String> stream1 = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz").stream();
        Stream<String> stream2 = stream1.filter((s) -> s.length() == 3);

        Iterator<String> i1 = stream2.iterator();
        Iterator<String> i2 = stream2.iterator();

        Assert.assertTrue(i1.hasNext());

        Assert.assertEquals("Foo", i2.next());
        Assert.assertEquals("Bar", i2.next());
        Assert.assertEquals("Baz", i2.next());

        Assert.assertFalse(i1.hasNext());
    }

}
