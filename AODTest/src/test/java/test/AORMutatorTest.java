package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AORMutatorTest {

    @Test
    public void test() {
        AORMutator m = new AORMutator();
        assertEquals(2, m.myMethod(1, 0));
    }

}
