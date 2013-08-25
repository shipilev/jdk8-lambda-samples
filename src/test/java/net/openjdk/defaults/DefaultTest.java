package net.openjdk.defaults;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultTest {

    public interface LegacyInterface {
        String legacyMethod();

        default String newMethod() {
            return "The beauty is in the eye of the defender";
        }
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

        @Override
        public String newMethod() {
            return "New Method from the New Class";
        }
    }

    @Test
    public void testLegacy() {
        assertEquals("Legacy Method from the Legacy Class", new LegacyClass().legacyMethod());
        assertEquals("The beauty is in the eye of the defender", new LegacyClass().newMethod());
    }

    @Test
    public void testNew() {
        assertEquals("Legacy Method from the New Class", new NewClass().legacyMethod());
        assertEquals("New Method from the New Class", new NewClass().newMethod());
    }

}
