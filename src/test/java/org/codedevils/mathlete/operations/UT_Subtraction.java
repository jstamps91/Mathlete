package org.jstamps.mathlete.operations;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Subtraction {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression("2-8");
		assertEquals(Double.valueOf(-6), Double.valueOf(exp.getSolution()));
		exp = new Expression("8-2");
		assertEquals(Double.valueOf(6), Double.valueOf(exp.getSolution()));
	}

	@Test
	public void testPrecedence() {
		Expression exp = new Expression("3-2*4");
		assertEquals(Double.valueOf(-5), Double.valueOf(exp.getSolution()));
		exp = new Expression("(3-2)*4");
		assertEquals(Double.valueOf(4), Double.valueOf(exp.getSolution()));
	}

}
