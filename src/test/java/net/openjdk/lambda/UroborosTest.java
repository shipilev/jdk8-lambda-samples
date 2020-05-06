package net.openjdk.lambda;

import org.junit.Test;

import static org.junit.Assert.*;

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

        assertEquals(10, depth);
    }

    @Test
    public void testStructured() {
        Uroboros tail = () -> null;
        Uroboros sect1 = () -> tail;
        Uroboros sect2 = () -> sect1;
        Uroboros sect3 = () -> sect2;
        Uroboros sect4 = () -> sect3;
        Uroboros sect5 = () -> sect4;
        Uroboros chain = () -> sect5;

        int depth = 0;
        while (chain != null) {
            depth++;
            chain = chain.bite();
        }

        assertEquals(7, depth);
    }

}