package org.jstamps.mathlete.functions;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_AbsoluteValue {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression(AbsoluteValue.getOperator().getToken() + "(-1.5)");
		assertEquals(Double.valueOf(1.5), Double.valueOf(exp.getSolution()));
		exp = new Expression(AbsoluteValue.getOperator().getToken() + "(1.5)");
		assertEquals(Double.valueOf(1.5), Double.valueOf(exp.getSolution()));
	}

}
