package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AODMutatorTest {

    @Test
    public void test() {
        AODMutator m = new AODMutator();
        assertEquals(2, m.myMethod(1, 0));
    }

}
