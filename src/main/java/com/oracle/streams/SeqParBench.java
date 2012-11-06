package com.oracle.streams;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class SeqParBench {

    private static final int COUNT = Integer.getInteger("count", 10_000_000);

    private List<Integer> list;

    @Setup
    public void setup() {
        Integer[] l = new Integer[COUNT];
        for (int c = 1; c <= COUNT; c++) {
            l[c - 1] = c;
        }
        list = Arrays.asList(l);
        Collections.shuffle(list);
    }

    @GenerateMicroBenchmark
    public Integer bench_1_Seq() {
        return list.stream().reduce(Math::max).get();
    }

    @GenerateMicroBenchmark
    public Integer bench_2_Par() {
        return list.parallel().reduce(Math::max).get();
    }

}
