package net.openjdk.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LazyEagerTest {

    private int invocations = 0;

    @Test
    public void testLazy() {
        Stream<String> stream = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> { invocations++; return s.length() == 3; });

        Iterator<String> i = stream.iterator();

        assertEquals("Foo", i.next());
        assertEquals(1, invocations);

        assertEquals("Bar", i.next());
        assertEquals(3, invocations);

        assertEquals("Baz", i.next());
        assertEquals(5, invocations);
    }

    @Test
    public void testEager() {
        List<String> list = Arrays.asList("Foo", "Marco", "Bar", "Polo", "Baz")
                .stream()
                .filter((s) -> { invocations++; return s.length() == 3; })
                .collect(Collectors.<String>toList());

        assertEquals(3, list.size());
        assertEquals(5, invocations);
    }

}
