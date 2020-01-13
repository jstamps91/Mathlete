package org.jstamps.mathlete.operations;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Power {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression("2^3");
		assertEquals(Double.valueOf(8), Double.valueOf(exp.getSolution()));
		exp = new Expression("3^2");
		assertEquals(Double.valueOf(9), Double.valueOf(exp.getSolution()));
	}

	@Test
	public void testPrecedence() {
		Expression exp = new Expression("3^2*4");
		assertEquals(Double.valueOf(36), Double.valueOf(exp.getSolution()));
		exp = new Expression("3^(2*4)");
		assertEquals(Double.valueOf(6561), Double.valueOf(exp.getSolution()));
	}

}
