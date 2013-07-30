package net.openjdk.lambda;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class ZamTest {

    @Test
    public void testUnsignedComparator() {
        Comparator<Integer> cmp = (x, y) -> Integer.compareUnsigned(x, y);
        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(1,  cmp.compare(-100, 100));
        assertEquals(-1, cmp.compare(100, -100));
    }

    @Test
    public void testUnsignedComparatorMRef() {
        Comparator<Integer> cmp = Integer::compareUnsigned;
        assertEquals(0,  cmp.compare(0, 0));
        assertEquals(1,  cmp.compare(-100, 100));
        assertEquals(-1, cmp.compare(100, -100));
    }

    @Test
    public void testUnsignedTreeSet() {
        NavigableSet<Integer> set = new TreeSet<>((x, y) -> Integer.compareUnsigned(x, y));
        set.addAll(Arrays.asList(-100, 0, 100));
        assertEquals(0,  set.first().intValue());
        assertEquals(-100, set.last().intValue());
    }

    @Test
    public void testUnsignedTreeSetMRef() {
        NavigableSet<Integer> set = new TreeSet<>(Integer::compareUnsigned);
        set.addAll(Arrays.asList(-100, 0, 100));
        assertEquals(0,  set.first().intValue());
        assertEquals(-100, set.last().intValue());
    }


    @Test
    public void testUnsignedTreeSetNotSerializable() {
        NavigableSet<Integer> set = new TreeSet<>((x, y) -> Integer.compareUnsigned(x, y));
        set.addAll(Arrays.asList(-100, 0, 100));
        assertEquals(0,  set.first().intValue());
        assertEquals(-100, set.last().intValue());
        byte[] serializedSet = writeSetToBytes(set, false);
        assertEquals(null, serializedSet );
    }


    @Test
    public void testUnsignedTreeSetSerializable() {
        NavigableSet<Integer> set = new TreeSet<>((Comparator<Integer> & Serializable) ((x, y) -> Integer.compareUnsigned(x, y)));
        set.addAll(Arrays.asList(-100, 0, 100));
        assertEquals(0,  set.first().intValue());
        assertEquals(-100, set.last().intValue());
        byte[] serializedSet = writeSetToBytes(set, true);
        NavigableSet<Integer> set1 = readSetFromBytes(serializedSet);
        assertEquals(0,  set1.first().intValue());
        assertEquals(-100, set1.last().intValue());
        assertEquals(set,  set1);
    }

    @Test
    public void testUnsignedTreeSetSerializableMRef() {
        NavigableSet<Integer> set = new TreeSet<>((Comparator<Integer> & Serializable) (Integer::compareUnsigned));
        set.addAll(Arrays.asList(-100, 0, 100));
        assertEquals(0,  set.first().intValue());
        assertEquals(-100, set.last().intValue());

        byte[] serializedSet = writeSetToBytes(set, true);
        NavigableSet<Integer> set1 = readSetFromBytes(serializedSet);

        assertEquals(0,  set1.first().intValue());
        assertEquals(-100, set1.last().intValue());
        assertEquals(set,  set1);
    }


    @SuppressWarnings("unchecked")
    private NavigableSet<Integer> readSetFromBytes(byte[] serializedSet) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedSet));
            return (NavigableSet<Integer>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            fail();
        }
        return null;
    }

    private byte[] writeSetToBytes(NavigableSet<Integer> set, boolean shouldFailIfNotSerializable) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(set);
            oos.flush();
            oos.close();
        } catch (NotSerializableException e) {
            if(shouldFailIfNotSerializable) {
                fail();
            }
            return null;
        } catch (IOException e) {
            fail();
        }
        return baos.toByteArray();
    }


}
