package net.openjdk.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ExternalInternalTest {

    @Test
    public void testExternal() {
        StringBuilder sb = new StringBuilder();

        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        for (String s : strings) {
            sb.append(s);
        }

        Assert.assertEquals(
                "FooBarBaz",
                sb.toString()
        );
    }

    @Test
    public void testInternal() {
        StringBuilder sb = new StringBuilder();

        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        strings.forEach((s) -> { sb.append(s); });

        Assert.assertEquals(
                "FooBarBaz",
                sb.toString()
        );
    }

}
