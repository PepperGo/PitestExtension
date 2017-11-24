package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class DemoTest {

	@Test
	public void test() {
		Demo d = new Demo();

		assertEquals(1 ,d.test(1, 0));
		assertEquals(1, d.test(1, 1));	//can fail with obbn
		
		assertEquals(0, d.test(2, 0)); //can fail with obbn
		
		assertEquals(3, d.test(2, 1));
		assertEquals(4, d.test(2, 2));
		
	}

}
