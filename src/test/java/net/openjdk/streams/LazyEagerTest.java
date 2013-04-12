package net.openjdk.streams;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEagerTest {

    private int invocations = 0;

    @Test
    public void testLazy() {
        Stream<String> stream = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> { invocations++; return s.length() == 3; });

        Iterator<String> i = stream.iterator();

        Assert.assertEquals("Foo", i.next());
        Assert.assertEquals(1, invocations);

        Assert.assertEquals("Bar", i.next());
        Assert.assertEquals(3, invocations);

        Assert.assertEquals("Baz", i.next());
        Assert.assertEquals(5, invocations);
    }

    @Test
    public void testEager() {
        List<String> list = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> { invocations++; return s.length() == 3; })
                .collect(Collectors.<String>toList());

        Assert.assertEquals(3, list.size());
        Assert.assertEquals(5, invocations);
    }

}
