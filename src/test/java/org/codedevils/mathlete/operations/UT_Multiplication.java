package org.jstamps.mathlete.operations;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Multiplication {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression("2*8");
		assertEquals(Double.valueOf(16), Double.valueOf(exp.getSolution()));
		exp = new Expression("8*2");
		assertEquals(Double.valueOf(16), Double.valueOf(exp.getSolution()));
	}

	@Test
	public void testPrecedence() {
		Expression exp = new Expression("3+2*4");
		assertEquals(Double.valueOf(11), Double.valueOf(exp.getSolution()));
		exp = new Expression("(3+2)*4");
		assertEquals(Double.valueOf(20), Double.valueOf(exp.getSolution()));
	}

}
