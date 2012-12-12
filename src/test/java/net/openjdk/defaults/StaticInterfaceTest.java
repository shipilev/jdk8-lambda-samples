package net.openjdk.defaults;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class StaticInterfaceTest {

    @Test
    public void test() {
        // FIXME: does not run with current public builds
//        Assert.assertEquals("toDublin", TicketOffice.defaultOffice().qDublin());
    }

    public interface TicketOffice {
        String qDublin();

        static TicketOffice defaultOffice() {
            return new TicketOffice() {
                @Override
                public String qDublin() {     // куда, блин?
                    return "toDublin";        // туда, блин!
                }
            };
        }
    }

}
