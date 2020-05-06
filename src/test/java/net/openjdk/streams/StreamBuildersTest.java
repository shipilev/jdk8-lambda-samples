package net.openjdk.streams;

import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamBuildersTest {

    @Test
    public void test1() {
        Stream<Integer> s = Stream.<Integer>builder().add(1).add(2).add(3).build();
        assertEquals(3, s.count());
    }

    @Test
    public void test2() {
        IntStream s = IntStream.builder().add(1).add(2).add(3).build();
        assertEquals(3, s.count());
    }

    @Test
    public void test3() {
        Stream<Integer> s = Stream.concat(
                Stream.<Integer>builder().add(1).build(),
                Stream.<Integer>builder().add(2).build()
        );

        assertEquals(2, s.count());
    }

    @Test
    public void test4() {
        DoubleStream avg = DoubleStream.generate(Math::random);
        double d = avg.limit(1000000).average().getAsDouble();
        assertEquals(0.5D, d, 0.001);
    }

    @Test
    public void test5() {
        int sum = IntStream.range(0, 1000).sum();
        assertEquals(500500 - 1000, sum);
    }

    @Test
    public void test6() {
        int sum = IntStream.range(0, 1000).reduce((r1, r2) -> r2 + r1).getAsInt();
        assertEquals(500500 - 1000, sum);
    }

    /*
    @Test
    public void test7() {
        String sum = Streams.zip(
                            Stream.generate(() -> "Prefix"),
                            IntStream.range(0, 1000).boxed(),
                            (r1, r2) -> r1 + r2)
                        .findFirst()
                        .get();
        assertEquals("Prefix0", sum);
    }

    @Test
    public void test8() {
        String sum = Streams.zip(
                            Stream.generate(() -> "Prefix"),
                            IntStream.range(0, 1000).boxed(),
                            (r1, r2) -> r1 + r2)
                        .findFirst()
                        .get();
        assertEquals("Prefix0", sum);
    }
    */

}
