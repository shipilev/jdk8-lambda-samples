package net.openjdk.streams;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.FlatMapper;
import java.util.stream.Stream;

public class StreamAPITest {

    @Test
    public void test1() {
        List<String> strings = Arrays.asList("Foo", "Bar", "Baz");
        Stream<String> stream = strings.stream();
        Assert.assertEquals(
                "Foo",
                stream.findFirst().get()
        );
    }

    @Test
    public void test2() {
        Assert.assertEquals(
                Arrays.asList("Bar", "Baz"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .filter((s) -> s.startsWith("B"))
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void test3() {
        Assert.assertEquals(
                Arrays.asList(3, 3, 3),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .map((s) -> s.length())
                        .boxed()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void test4() {
        Assert.assertEquals(
                9,
                Arrays.asList("Foo", "BarBar", "BazBazBaz")
                        .stream()
                        .map(s -> s.length())
                        .reduce((l, r) -> (l > r ? l : r))
                        .getAsInt()
        );
    }

    @Test
    public void test5() {
        // FIXME: explicit MultiFunction to dodge javac type inference deficiency
        FlatMapper<String, String> multiplicator = (element, sink) -> Arrays.stream(element.split(" ")).forEach(sink);
        Assert.assertEquals(
                Arrays.asList("Foo", "Bar", "Baz"),
                Arrays.asList("Foo Bar Baz")
                        .stream()
                        .flatMap(multiplicator)
                        .collect(Collectors.toList())
        );
    }

    @SuppressWarnings("serial")
    @Test
    public void test6() {
        Assert.assertEquals(
                new ArrayList<String>() {{ add("Foo"); add("Bar"); add("Baz"); }},
                Arrays.asList("Foo", "Bar", "Baz", "Baz", "Foo", "Bar")
                        .stream()
                        .distinct()
                        .collect(Collectors.toList())
        );
    }

    @Test
    public void test7() {
        Assert.assertEquals(
                Arrays.asList("Bar", "Baz", "Foo"),
                Arrays.asList("Foo", "Bar", "Baz")
                        .stream()
                        .sorted((o1, o2) -> o1.compareTo(o2))
                        .collect(Collectors.toList())
        );
    }


}
