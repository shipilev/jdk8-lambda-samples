package com.oracle.streams;

import oracle.micro.api.annotations.GenerateMicroBenchmark;
import oracle.micro.api.annotations.OutputTimeUnit;
import oracle.micro.api.annotations.Setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class SeqParBench {

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

//    @GenerateMicroBenchmark
    public Integer bench_1_Seq() {
        return list.stream().reduce(Math::max).get();
    }

//    @GenerateMicroBenchmark
    public Integer bench_2_Par() {
        return list.parallel().reduce(Math::max).get();
    }

}
