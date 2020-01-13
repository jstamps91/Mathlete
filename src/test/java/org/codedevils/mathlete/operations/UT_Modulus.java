package org.jstamps.mathlete.operations;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Modulus {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression("2%9");
		assertEquals(Double.valueOf(2), Double.valueOf(exp.getSolution()));
		exp = new Expression("9%2");
		assertEquals(Double.valueOf(1), Double.valueOf(exp.getSolution()));
	}

	@Test
	public void testPrecedence() {
		Expression exp = new Expression("3+15%4");
		assertEquals(Double.valueOf(6), Double.valueOf(exp.getSolution()));
		exp = new Expression("(3+15)%4");
		assertEquals(Double.valueOf(2), Double.valueOf(exp.getSolution()));
	}

}
