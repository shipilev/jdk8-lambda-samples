package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.functions.Factory;

public class NoiseSampleTest {

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

    Map<String, Map<String, Counter>> map;

    @Test
    public void test() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<>(
                        new Factory<Map<String, Counter>>() {
                            @Override
                            public Map<String, Counter> make() {
                                return new ComputeMap<>(
                                        new Factory<Counter>() {
                                            @Override
                                            public Counter make() {
                                                return new Counter();
                                            }
                                        }
                                );
                            }
                        }
                );

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL0() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<String, Map<String, Counter>>(() -> new ComputeMap<>(() -> new Counter()));

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL1() {
        Factory<Map<String, Counter>> mapFactory = () -> new ComputeMap<>(() -> new Counter());
        Map<String, Map<String, Counter>> map = new ComputeMap<>(mapFactory);

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL2() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<>((Factory<Map<String, Counter>>) () -> new ComputeMap<>(() -> new Counter()));

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testR() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<String, Map<String, Counter>>(() -> new ComputeMap<>(Counter::new));

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    /**
     * Computing map.
     *
     * @param <K>
     * @param <V>
     */
    @SuppressWarnings("serial")
    public static class ComputeMap<K, V> extends HashMap<K, V> {

        private final Factory<V> factory;

        public ComputeMap(Factory<V> factory) {
            this.factory = factory;
        }

        @Override
        public V get(Object key) {
            @SuppressWarnings("unchecked")
            K k = (K) key;
            V v = super.get(key);
            if (v == null) {
                v = factory.make();
                super.put(k, v);
            }
            return v;
        }
    }

}
