/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UOIMutatorTest {

    @Test
    public void test() {
        UOIMutator m = new UOIMutator();
        assertEquals(2, m.myMethod(1, 0));
    }

}
