package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ABSMutatorTest {

	@Test
	public void test() {
		ABSMutator d = new ABSMutator();

		assertEquals(-1 ,d.test(1));
		
	}

}
