package com.oracle.defenders;

import junit.framework.Assert;
import org.junit.Test;

public class DefenderTest {

    @Test
    public void testNew() {
        Assert.assertEquals("Legacy Method from the New Class", new NewClass().legacyMethod());
//        Assert.assertEquals("New Method from the New Class", new NewClass().newMethod());
    }

    @Test
    public void testLegacy() {
        Assert.assertEquals("Legacy Method from the Legacy Class", new LegacyClass().legacyMethod());
//        Assert.assertEquals("The beauty is in the eye of the defender", new LegacyClass().newMethod());
    }

    public interface LegacyInterface {
        String legacyMethod();
//        String newMethod();
    }

    public class LegacyClass implements LegacyInterface {
        @Override
        public String legacyMethod() {
            return "Legacy Method from the Legacy Class";
        }
    }

    public class NewClass implements LegacyInterface {
        @Override
        public String legacyMethod() {
            return "Legacy Method from the New Class";
        }

//        @Override
//        public String newMethod() {
//            return "New Method from the New Class";
//        }
    }

//    default { return "The beauty is in the eye of the defender"; }


}
