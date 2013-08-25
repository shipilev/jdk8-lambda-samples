package net.openjdk.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class MethodRefTest {

    @Test
    public void testMethodRefStatic() {
        Comparator<Integer> cmp = Integer::compare;

        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testMethodRefInstance0() {
    //  Consumer<String> b = s -> System.out.println(s);
        Consumer<String> b = System.out::println;
        Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar").forEach(b);
    }

    private Predicate<String> makeCaseUnsensitiveMatcher(String pattern) {
        return pattern::equalsIgnoreCase;
    }

    @Test
    public void testMethodRefInstance1() {
        // Predicate<T>   ~  boolean test(T t);
        assertTrue( makeCaseUnsensitiveMatcher("true").test("TruE") );
        assertTrue( makeCaseUnsensitiveMatcher("false").test("FalsE") );
        assertFalse(makeCaseUnsensitiveMatcher("true").test("FalsE") );
    }

    @Test
    public void testMethodRefInstance2() {
        // Predicate<T>   ~  boolean test(T t);
        Predicate<String> isTrue = "true"::equalsIgnoreCase;
        assertTrue(isTrue.test("TruE"));
        assertFalse(isTrue.test("FalsE"));
    }

    @Test
    public void testMethodRefInstanceUnbound() {
        // Comparator<T>  ~ int compare(T o1, T o2);
        // Integer        ~ int compareTo(Integer anotherInteger)
        Comparator<Integer> cmp = Integer::compareTo;

        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testConstructor(){
        Function<String, Integer> f = Integer::new;
        assertEquals(Integer.valueOf(0), f.apply("0"));
        assertEquals(Integer.valueOf(1), f.apply("1"));
    }

    public static class Counter {

        public Counter() {
            this(0);
        }

        public Counter(int count) {
            this.count = count;
        }

        private int count = 0;

        public int incCount() {
            return ++count;
        }

        public int getCount() {
            return count;
        }
    }

    @Test
    public void testConstructor0(){
        Supplier<Counter> f = Counter::new;
        assertEquals(0, f.get().getCount());
        assertEquals(1, f.get().incCount());
    }

    @Test
    public void testConstructor1(){
        Function<Integer, Counter> f = Counter::new;
        assertEquals(1, f.apply(1).getCount());
        assertEquals(42, f.apply(42).getCount());
    }

}
