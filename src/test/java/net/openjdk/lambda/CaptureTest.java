package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Predicate;

public class CaptureTest {

    @Test
    public void testLegacy() {
        final int minus_one = -1;
        final int zero = 0;
        final int one  = 1;

        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return (x < y) ? minus_one : ((x > y) ? one : zero);
            }

        };

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }

    @Test
    public void testLambda() {
        int minus_one = -1;
        int zero = 0;
        int one  = 1;

        Comparator<Integer> cmp = (x, y) -> (x < y) ? minus_one : ((x > y) ? one : zero);

        Assert.assertEquals(0,  cmp.compare(0, 0));
        Assert.assertEquals(-1, cmp.compare(-100, 100));
        Assert.assertEquals(1,  cmp.compare(100, -100));
    }


    private Predicate<String> makeCaseUnsensitiveMatcher(String pattern) {
        return s -> pattern.equalsIgnoreCase(s);
    }

    @Test
    public void testLambda1() {
        // Predicate<T>   ~  boolean test(T t);
        Assert.assertTrue( makeCaseUnsensitiveMatcher("true").test("TruE") );
        Assert.assertTrue( makeCaseUnsensitiveMatcher("false").test("FalsE") );
        Assert.assertFalse(makeCaseUnsensitiveMatcher("true").test("FalsE") );

    }

}
