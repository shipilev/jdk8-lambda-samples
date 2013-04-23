package com.oracle.lambda;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class CaptureBench {

    public interface Fooable {
        public Object foo();
    }

    public volatile int wh0 = 0;

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    public Fooable capture_inner_0() {
        return new Fooable() {
            @Override
            public Object foo() {
                return "42";
            }
        };
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    public Fooable capture_inner_1() {
        final int l0 = wh0;
        return new Fooable() {
            @Override
            public Object foo() {
                return "42" + l0;
            }
        };
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    public Fooable capture_lambda_0() {
        return () -> "42";
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    public Fooable capture_lambda_1() {
        final int l0 = wh0;
        return () -> "42" + l0;
    }

    private Fooable cached0 = new Fooable() {
        @Override
        public Object foo() {
            return "42";
        }
    };

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    public Fooable capture_innerCached_0() {
        return cached0;
    }

    /**
     * Execute with:
     *  $ java -jar target/microbenchmarks.jar ".*CaptureBench.*" -wi 3 -i 5 -r 1
     */

}
