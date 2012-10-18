package net.openjdk.lambda;

import junit.framework.Assert;
import org.junit.Test;

public class UroborosTest {

    interface Uroboros {
        Uroboros bite();
    }

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

}