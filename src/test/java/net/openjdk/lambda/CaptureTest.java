package net.openjdk.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class CaptureTest {

    @Test
    public void testLegacy() {
        final int minus_one = -1;
        final int zero = 0;
        final int one = 1;

        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? minus_one : ((x > y) ? one : zero);
            }

        };

        assertEquals(0, cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1, cmp.compare(100, -100));
    }

    @Test
    public void testLambda() {
        int minus_one = -1;
        int zero = 0;
        int one = 1;

        Comparator<Integer> cmp = (x, y) -> (x < y) ? minus_one : ((x > y) ? one : zero);

        assertEquals(0, cmp.compare(0, 0));
        assertEquals(-1, cmp.compare(-100, 100));
        assertEquals(1, cmp.compare(100, -100));
    }

    private Predicate<String> makeCaseUnsensitiveMatcher(String pattern) {
        return s -> pattern.equalsIgnoreCase(s);
    }

    @Test
    public void testPredicate1() {
        // Predicate<T>   ~  boolean test(T t);
        assertTrue(makeCaseUnsensitiveMatcher("true").test("TruE"));
        assertTrue(makeCaseUnsensitiveMatcher("false").test("FalsE"));
        assertFalse(makeCaseUnsensitiveMatcher("true").test("FalsE"));
    }

    @Test
    public void testPredicate2() {
        Function<String, Predicate<String>> matcher = s1 -> s2 -> s1.equalsIgnoreCase(s2);

        assertTrue(matcher.apply("true").test("TruE"));
        assertTrue(matcher.apply("false").test("FalsE"));
        assertFalse(matcher.apply("true").test("FalsE"));
    }

}
