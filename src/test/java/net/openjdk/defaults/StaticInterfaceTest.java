package net.openjdk.defaults;

import org.junit.Test;

import static org.junit.Assert.*;

public class StaticInterfaceTest {

    @Test
    public void test() {
        assertEquals("toDublin", TicketOffice.defaultOffice().qDublin());
    }

    public interface TicketOffice {
        String qDublin();

        static TicketOffice defaultOffice() {
            return () -> "toDublin";
//            return new TicketOffice() {
//                @Override
//                public String qDublin() {     // куда, блин?
//                    return "toDublin";        // туда, блин!
//                }
//            };
        }
    }

}
