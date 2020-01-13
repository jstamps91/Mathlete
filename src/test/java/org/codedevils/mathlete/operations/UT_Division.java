package org.jstamps.mathlete.operations;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Division {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression("2/8");
		Double expected = Double.valueOf(1.0/4);
		boolean isEqual = expected.equals(exp.getSolution());
		assertTrue(isEqual);
		exp = new Expression("8/2");
		expected = Double.valueOf(4);
		isEqual = expected.equals(exp.getSolution());
		assertTrue(isEqual);
	}

	@Test
	public void testPrecedence() {
		Expression exp = new Expression("3+2/4");		
		Double expected = Double.valueOf(3.5);
		boolean isEqual = expected.equals(exp.getSolution());
		assertTrue(isEqual);
		exp = new Expression("(3+2)/4");		
		expected = Double.valueOf(5/4.0);
		isEqual = expected.equals(exp.getSolution());
		assertTrue(isEqual);
	}

}
