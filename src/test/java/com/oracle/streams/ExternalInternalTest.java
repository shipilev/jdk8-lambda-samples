package com.oracle.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ExternalInternalTest {

    @Test
    public void testExternal() {
        StringBuilder sb = new StringBuilder();

        for (String s : Arrays.asList("Foo", "Bar", "Baz")) {
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

        Arrays.asList("Foo", "Bar", "Baz")
                .forEach((s) -> { sb.append(s); });

        Assert.assertEquals(
                "FooBarBaz",
                sb.toString()
        );
    }

}
