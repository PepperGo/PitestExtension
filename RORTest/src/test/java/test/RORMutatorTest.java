package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RORMutatorTest {

    @Test
    public void test() {
        RORMutator r = new RORMutator();
        assertEquals(1, r.myMethod(1, 0));
        assertEquals(1, r.myMethod(0, 1));
    }

}
