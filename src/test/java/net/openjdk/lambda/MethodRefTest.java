package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.functions.Block;
import java.util.functions.Factory;
import java.util.functions.Mapper;
import java.util.functions.Predicate;

public class MethodRefTest {

    @Test
    public void testMethodRefStatic() {
        Comparator<Integer> cmp = Integer::compare;

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testMethodRefInstance0() {
    //  Block<String> b = s -> System.out.println(s);
        Block<String> b = System.out::println;
        Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar").forEach(b);
    }

    private Predicate<String> makeCaseUnsensitiveMatcher(String pattern) {
        return pattern::equalsIgnoreCase;
    }

    @Test
    public void testMethodRefInstance1() {
        // Predicate<T>   ~  boolean test(T t);
        Assert.assertTrue( makeCaseUnsensitiveMatcher("true").test("TruE") );
        Assert.assertTrue( makeCaseUnsensitiveMatcher("false").test("FalsE") );
        Assert.assertFalse(makeCaseUnsensitiveMatcher("true").test("FalsE") );
    }

    @Test
    public void testMethodRefInstance2() {
        // Predicate<T>   ~  boolean test(T t);
        Predicate<String> isTrue = "true"::equalsIgnoreCase;
        Assert.assertTrue(isTrue.test("TruE"));
        Assert.assertFalse(isTrue.test("FalsE"));
    }

    @Test
    public void testMethodRefInstanceUnbound() {
        // Comparator<T>  ~ int compare(T o1, T o2);
        // Integer        ~ int compareTo(Integer anotherInteger)
        Comparator<Integer> cmp = Integer::compareTo;

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }


    @Test
    public void testParseInt(){
        Mapper<String, Integer> f = Integer::parseInt;
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    @Test
    public void testConstructor(){
        Mapper<String, Integer> f = Integer::new;
        Assert.assertEquals(Integer.valueOf(0), f.map("0"));
        Assert.assertEquals(Integer.valueOf(1), f.map("1"));
    }

    public static class Counter {

        public Counter() {
            this(0);
        }

        public Counter(int count) {
            this.count = count;
        }

        private int count = 0;

        public int inc() {
            return ++count;
        }

        public int get() {
            return count;
        }
    }

    @Test
    public void testConstructor0(){
        Factory<Counter> f = Counter::new;
        Assert.assertEquals(0, f.make().get());
        Assert.assertEquals(1, f.make().inc());
    }

    @Test
    public void testConstructor1(){
        Mapper<Integer, Counter> f = Counter::new;
        Assert.assertEquals(1, f.map(1).get());
        Assert.assertEquals(42, f.map(42).get());
    }

}
