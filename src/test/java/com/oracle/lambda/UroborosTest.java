package com.oracle.lambda;

import junit.framework.Assert;
import org.junit.Test;

public class UroborosTest {

    @Test
    public void test() {
        Uroboros chain = () -> () -> () -> () -> () -> () -> () -> () -> () -> () -> null;

        int depth = 0;
        while (chain != null) {
            depth++;
            chain = chain.bite();
        }

        Assert.assertEquals(10, depth);
    }

    interface Uroboros {
        Uroboros bite();
    }

}