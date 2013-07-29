package net.openjdk.streams;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class ShortCircuitTest {

    int invocations;

    @Test
    public void test01() {
        invocations = 0;
        int first = IntStream.range(1, 1000)
                .filter(x -> { invocations++; return (x % 42) == 0; })
                .findFirst().getAsInt();
        Assert.assertEquals(42, first);
        Assert.assertEquals(42, invocations);
    }

    @Test
    public void test02() {
        invocations = 0;
        int first = IntStream.range(1, 1000)
                .parallel()
                .filter(x -> { invocations++; return (x % 42) == 0; })
                .findFirst().getAsInt();
        Assert.assertEquals(42, first);

        // This assert will fail: parallel streams can evaluate more
//        Assert.assertEquals(42, invocations);
    }

    @Test
    public void test03() {
        invocations = 0;
        int first = IntStream.range(1, 1000)
                .unordered()
                .parallel()
                .filter(x -> { invocations++; return (x % 42) == 0; })
                .findFirst().getAsInt();

        // This assert can also fail: unordered streams are not having any particular order
//        Assert.assertEquals(42, first);

        // This assert will fail: parallel streams can evaluate more
//        Assert.assertEquals(42, invocations);
    }

}
