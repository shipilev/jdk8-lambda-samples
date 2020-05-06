package net.openjdk.lambda;

import org.junit.Test;

import java.util.function.Supplier;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ScopingTest {

    class MasterClassAnonymous {

        Supplier<String> sa1 = new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("this.toString() = " + this.toString());
                return this.toString();
            }
        };

        Supplier<String> sa2 = new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("toString() = " + toString());
                return toString();
            }
        };

        public String toString() {
            return "I am a Master!";
        }
    }

    class MasterClassLambda {
        Supplier<String> sl1 = () -> this.toString();
        Supplier<String> sl2 = () -> toString();

        public String toString() {
            return "I am a Master!";
        }
    }

    @Test
    public void testScoping1() throws Exception {
        assertThat(new MasterClassAnonymous().sa1.get(), is(not("I am a Master!")));
        assertThat(new MasterClassAnonymous().sa2.get(), is(not("I am a Master!")));

        assertThat(new MasterClassLambda().sl1.get(), is("I am a Master!"));
        assertThat(new MasterClassLambda().sl2.get(), is("I am a Master!"));
    }

}
