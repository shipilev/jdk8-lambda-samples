package net.openjdk.defaults;

import com.google.common.collect.TreeMultiset;
import junit.framework.Assert;
import org.junit.Test;

public class GuavaTest {

    @Test
    public void testTreeMultiset() {
        TreeMultiset<String> strings = TreeMultiset.create();
        strings.add("Foo");
        strings.add("Bar");
        strings.add("Baz");

        String result = strings.stream().reduce((s1, s2) -> s1 + ", " + s2).get();
        Assert.assertEquals("Bar, Baz, Foo", result);
    }

}
