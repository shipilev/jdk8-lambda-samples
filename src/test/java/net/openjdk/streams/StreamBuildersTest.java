package net.openjdk.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.stream.Streams;

public class StreamBuildersTest {

    @Test
    public void test1() {
        Stream<Integer> s = Stream.<Integer>builder().add(1).add(2).add(3).build();
        Assert.assertEquals(3, s.count());
    }

    @Test
    public void test2() {
        IntStream s = IntStream.builder().add(1).add(2).add(3).build();
        Assert.assertEquals(3, s.count());
    }

    @Test
    public void test3() {
        Stream<Integer> s = Streams.concat(
                Stream.<Integer>builder().add(1).build(),
                Stream.<Integer>builder().add(2).build()
        );

        Assert.assertEquals(2, s.count());
    }

    @Test
    public void test4() {
        DoubleStream avg = DoubleStream.generate(Math::random);
        double d = avg.limit(1000000).average().getAsDouble();
        Assert.assertEquals(0.5D, d, 0.001);
    }

    @Test
    public void test5() {
        int sum = IntStream.range(0, 1000).sum();
        Assert.assertEquals(500500 - 1000, sum);
    }

    @Test
    public void test6() {
        int sum = IntStream.range(0, 1000).reduce((r1, r2) -> r2 + r1).getAsInt();
        Assert.assertEquals(500500 - 1000, sum);
    }

    @Test
    public void test7() {
        String sum = Streams.zip(
                            Stream.generate(() -> "Prefix"),
                            IntStream.range(0, 1000).boxed(),
                            (r1, r2) -> r1 + r2)
                        .findFirst()
                        .get();
        Assert.assertEquals("Prefix0", sum);
    }

    @Test
    public void test8() {
        String sum = Streams.zip(
                            Stream.generate(() -> "Prefix"),
                            IntStream.range(0, 1000).boxed(),
                            (r1, r2) -> r1 + r2)
                        .findFirst()
                        .get();
        Assert.assertEquals("Prefix0", sum);
    }

}
