package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OBBNMutatorTest {

    @Test
    public void test() {
        OBBNMutator m = new OBBNMutator();
        assertEquals(0, m.myMethod(1, 0));
        assertEquals(1, m.myMethod(1, 1));
    }

}
