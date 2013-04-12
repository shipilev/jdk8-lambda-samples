package com.oracle.streams;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class LazyEagerBench {

    private static final int COUNT = Integer.getInteger("count", 10_000_000);

    private List<Integer> list;

    @Setup
    public void setup() {
        list = new ArrayList<>(COUNT);
        for (int c = 1; c <= COUNT; c++) {
            list.add(c);
        }
        Collections.shuffle(list);
    }

    @GenerateMicroBenchmark
    public int bench_1_Iterator() {
        Counter c = new Counter();
        Iterator<Integer> iterator = list.stream()
                .filter((i) -> (i & 0xF) == 0)
                .filter((i) -> (i & 0xFF) == 0)
                .filter((i) -> (i & 0xFFF) == 0)
                .filter((i) -> (i & 0xFFFF) == 0)
                .iterator();

        while (iterator.hasNext()) {
            c.add(iterator.next());
        }
        return c.sum;
    }

    @GenerateMicroBenchmark
    public int bench_2_ForEach() {
        Counter c = new Counter();
        list.stream()
                .filter((i) -> (i & 0xF) == 0)
                .filter((i) -> (i & 0xFF) == 0)
                .filter((i) -> (i & 0xFFF) == 0)
                .filter((i) -> (i & 0xFFFF) == 0)
                .forEach(c::add);
        return c.sum;
    }

    private class Counter {
        public int sum;
        void add(int i) {
            sum += i;
        }
    }

}
