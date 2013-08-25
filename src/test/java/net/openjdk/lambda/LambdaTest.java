package net.openjdk.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class LambdaTest {

    @Test
    public void testCmpLegacy() {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? -1 : ((x > y) ? 1 : 0);
            }

        };

        assertEquals(0, cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda0() {
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda1() {
        Comparator<Integer> cmp = (Integer x, Integer y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testNoArgs() {
        // Supplier<T>  ~   T get();
        Supplier<Integer> ultimateAnswerFactory = () -> 42;
        assertTrue(42 == ultimateAnswerFactory.get());
    }

    @Test
    public void testOneArg0() {
        // Function<T, R>  ~   R apply(T t);
        Function<String, Integer> f = (String s) -> Integer.parseInt(s);
        assertEquals(Integer.valueOf(0), f.apply("0"));
        assertEquals(Integer.valueOf(1), f.apply("1"));
    }

    @Test
    public void testOneArg1() {
        Function<String, Integer> f = (s) -> Integer.parseInt(s);
        assertEquals(Integer.valueOf(0), f.apply("0"));
        assertEquals(Integer.valueOf(1), f.apply("1"));
    }

    @Test
    public void testOneArg2() {
        Function<String, Integer> f = s -> Integer.parseInt(s);
        assertEquals(Integer.valueOf(0), f.apply("0"));
        assertEquals(Integer.valueOf(1), f.apply("1"));
    }

    @Test
    public void testCmpLambda2() { // wrong
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);

        assertEquals(0,  cmp.compare(100, 100));
        assertEquals(-1, cmp.compare(0, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda3() { // right
        Comparator<Integer> cmp = (a, b) -> {
            int x = a;
            int y = b;
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        };

        assertEquals(0,  cmp.compare(1000, 1000));
        assertEquals(-1, cmp.compare(0, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testConsumer() {
        // Consumer<T>  ~  void accept(T t);
        Consumer<String> b = s -> { System.out.println(s);};
        Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar").forEach(b);
    }

}
