package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.functions.Mapper;

public class NoiseSampleTest {

    @Test
    public void test() {
        Map<String, SortedMap<String, Counter>> map =
                new ComputeTreeMap<>(
                        new Mapper<String, SortedMap<String, Counter>>() {
                            @Override
                            public SortedMap<String, Counter> map(String s) {
                                return new ComputeTreeMap<>(
                                        new Mapper<String, Counter>() {
                                            @Override
                                            public Counter map(String s) {
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
    public void testL() {
        Map<String, SortedMap<String, Counter>> map =
                new ComputeTreeMap<>((s) -> new ComputeTreeMap<>((_) -> new Counter()));

        Assert.assertEquals(1, map.get("foo").get("bar").inc());
        Assert.assertEquals(2, map.get("foo").get("bar").inc());
    }

    /**
     * Computing map.
     *
     * @param <K>
     * @param <V>
     */
    public class ComputeTreeMap<K, V> extends TreeMap<K,V> {

        private final Mapper<K, V> map;

        public ComputeTreeMap(Mapper<K, V> map) {
            this.map = map;
        }

        @Override
        public V get(Object key) {
            K k = (K) key;
            V v = super.get(key);
            if (v == null) {
                v = map.map(k);
                super.put(k, v);
            }
            return v;
        }
    }

    public class Counter {
        private int count = 0;
        public int inc() {
            return ++count;
        }
    }

}
