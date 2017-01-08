package jp.gr.norinori;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class MathTest {

	@Test
	public void testCombination() {
		BigInteger result;

		result = Math.combination(4, 2);
		assertEquals(6, result.intValue());

		result = Math.combination(5, 2);
		assertEquals(10, result.intValue());

		result = Math.combination(6, 3);
		assertEquals(20, result.intValue());
	}
}
