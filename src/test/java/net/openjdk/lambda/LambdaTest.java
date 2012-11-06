package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.functions.Block;
import java.util.functions.Factory;
import java.util.functions.Mapper;

public class LambdaTest {

    @Test
    public void testCmpLegacy() {
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? -1 : ((x > y) ? 1 : 0);
            }

        };

        Assert.assertEquals(0, cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda0() {
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda1() {
        Comparator<Integer> cmp = (Integer x, Integer y) -> (x < y) ? -1 : ((x > y) ? 1 : 0);

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testNoArgs() {
        // Factory<T>  ~   T make();
        Factory<Integer> ultimateAnswerFactory = () -> 42;
        Assert.assertTrue(42 == ultimateAnswerFactory.make());
    }

    @Test
    public void testOneArg0() {
        // Mapper<R, T>  ~    R map(T t);
        Mapper<Integer, String> f = (String s) -> Integer.parseInt(s);
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    @Test
    public void testOneArg1() {
        Mapper<Integer, String> f = (s) -> Integer.parseInt(s);
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    @Test
    public void testOneArg2() {
        Mapper<Integer, String> f = s -> Integer.parseInt(s);
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    @Test
    public void testCmpLambda2() { // wrong
        Comparator<Integer> cmp = (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1);

        Assert.assertEquals(0,  cmp.compare(100, 100));
        Assert.assertEquals(-1, cmp.compare(0, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testCmpLambda3() { // right
        Comparator<Integer> cmp = (a, b) -> {
            int x = a;
            int y = b;
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        };

        Assert.assertEquals(0,  cmp.compare(1000, 1000));
        Assert.assertEquals(-1, cmp.compare(0, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testBlock0() {
        // Block<T>  ~  void apply(T t);
        Block<String> b = s -> { System.out.println(s);};
        Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar").forEach(b);
    }

    @Test
    public void testBlock1() {
        // Block<T>  ~  void apply(T t);
        Block<String> b = s -> System.out.println(s);
        Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar").forEach(b);
    }

}
