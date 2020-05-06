package net.openjdk.lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class NoiseSampleTest {

    /**
     * Mutable counter.
     */
    public static class Counter {

        private int count = 0;

        public Counter() {
            this(0);
        }

        public Counter(int count) {
            this.count = count;
        }

        public int inc() {
            return ++count;
        }

        public int get() {
            return count;
        }
    }

    /**
     * This is a weird collection we need to match
     */
    Map<String, Map<String, Counter>> map;

    /**
     * Computing map.
     *
     * @param <K>
     * @param <V>
     */
    @SuppressWarnings("serial")
    public static class ComputeMap<K, V> extends HashMap<K, V> {

        private final Supplier<V> factory;

        public ComputeMap(Supplier<V> factory) {
            this.factory = factory;
        }

        @Override
        public V get(Object key) {
            @SuppressWarnings("unchecked")
            K k = (K) key;
            V v = super.get(key);
            if (v == null) {
                v = factory.get();
                super.put(k, v);
            }
            return v;
        }
    }

    @Test
    public void test() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<>(
                        new Supplier<Map<String, Counter>>() {
                            @Override
                            public Map<String, Counter> get() {
                                return new ComputeMap<>(
                                        new Supplier<Counter>() {
                                            @Override
                                            public Counter get() {
                                                return new Counter();
                                            }
                                        }
                                );
                            }
                        }
                );

        assertEquals(1, map.get("foo").get("bar").inc());
        assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL0() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<String, Map<String, Counter>>(() -> new ComputeMap<>(() -> new Counter()));

        assertEquals(1, map.get("foo").get("bar").inc());
        assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL1() {
        Supplier<Map<String, Counter>> mapFactory = () -> new ComputeMap<>(() -> new Counter());
        Map<String, Map<String, Counter>> map = new ComputeMap<>(mapFactory);

        assertEquals(1, map.get("foo").get("bar").inc());
        assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testL2() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<>((Supplier<Map<String, Counter>>) () -> new ComputeMap<>(() -> new Counter()));

        assertEquals(1, map.get("foo").get("bar").inc());
        assertEquals(2, map.get("foo").get("bar").inc());
    }

    @Test
    public void testR() {
        Map<String, Map<String, Counter>> map =
                new ComputeMap<String, Map<String, Counter>>(() -> new ComputeMap<>(Counter::new));

        assertEquals(1, map.get("foo").get("bar").inc());
        assertEquals(2, map.get("foo").get("bar").inc());
    }

}
