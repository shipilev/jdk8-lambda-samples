package net.openjdk.streams;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SeqParTest {

    private static final int COUNT = Integer.getInteger("count", 1_000_000);

    private List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList<>(COUNT);
        for (int c = 1; c <= COUNT; c++) {
            list.add(c);
        }
        Collections.shuffle(list);
    }

    @Test
    public void testSeq() {
        assertEquals(
                Integer.valueOf(COUNT),
                list.stream().reduce(Math::max).get()
        );
    }

    @Test
    public void testPar() {
        assertEquals(
                Integer.valueOf(COUNT),
                list.parallelStream().reduce(Math::max).get()
        );
    }

}
