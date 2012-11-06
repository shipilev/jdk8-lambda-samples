package net.openjdk.misc;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Sample from:
 * http://ru-java.livejournal.com/1099136.html
 */
//public class CollectionTest {
//
//    @Test
//    @Ignore // FIXME: MapStream.sorted is not yet implemented ;)
//    public void test() {
//        Collection<MyStruct> coll = new ArrayList<MyStruct>() {
//            { add(new MyStruct(1, 42)); }
//            { add(new MyStruct(1, 43)); }
//            { add(new MyStruct(1, 44)); }
//            { add(new MyStruct(2, 142)); }
//            { add(new MyStruct(2, 143)); }
//            { add(new MyStruct(2, 144)); }
//        };
//
//        Integer id = coll.stream()
//                .reduceBy((m) -> m.id, () -> 0, (base, x) -> base + x.value)    /* Map<Id, Sum{Value}> */
//                .stream()
//                .swap()                                                         /* Map<Sum{Value}, Id> */
//                .sorted(Integer::compareTo)                                     /* Map<Sorted(Sum{Value}), Id> */
//                .values()                                                       /* Coll<Id> */
//                .findFirst()                                                    /* Optional<Id> */
//                .get();                                                         /* Id */
//
//        Assert.assertEquals(Integer.valueOf(2), id);
//
//        MyStruct result = coll.stream()
//                .filter((m) -> (m.id == id))                                    /* All with the required ID */
//                .reduce((x, y) -> (x.value > y.value ? y : x))                  /* Optional<MyStruct> with the minimal value */
//                .get();                                                         /* MyStruct with the minimal value */
//
//        Assert.assertEquals(new MyStruct(2, 142), result);
//    }
//
//    class MyStruct {
//        int id;
//        int value;
//
//        MyStruct(int id, int value) {
//            this.id = id;
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//
//            MyStruct myStruct = (MyStruct) o;
//
//            if (id != myStruct.id) {
//                return false;
//            }
//            if (value != myStruct.value) {
//                return false;
//            }
//
//            return true;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = id;
//            result = 31 * result + value;
//            return result;
//        }
//    };
//
//}
