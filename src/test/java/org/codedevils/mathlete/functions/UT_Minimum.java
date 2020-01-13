package org.jstamps.mathlete.functions;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Minimum {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression(Minimum.getOperator().getToken() + "(1.5,15)");
		assertEquals(Double.valueOf(1.5), Double.valueOf(exp.getSolution()));
	}

}
