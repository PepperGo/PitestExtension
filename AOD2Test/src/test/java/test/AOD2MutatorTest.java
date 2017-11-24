package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class AOD2MutatorTest {

	@Test
	public void test() {
		AOD2Mutator d = new AOD2Mutator();

		assertEquals(3 ,d.test(0, 1));
		
	}

}
