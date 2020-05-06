package net.openjdk.defaults;

import com.google.common.collect.TreeMultiset;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuavaTest {

    @Test
    public void testTreeMultiset() {
        TreeMultiset<String> strings = TreeMultiset.create();
        strings.add("Foo");
        strings.add("Bar");
        strings.add("Baz");

        String result = strings.stream().reduce((s1, s2) -> s1 + ", " + s2).get();
        assertEquals("Bar, Baz, Foo", result);
    }

}
