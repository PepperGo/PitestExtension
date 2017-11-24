/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CRCRMutatorTest {
    @Test
    public void test() {
        CRCRMutator m = new CRCRMutator();
        assertEquals(4, m.myMethod(1, 2));
    }
}
