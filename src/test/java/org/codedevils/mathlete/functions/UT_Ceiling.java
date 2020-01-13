package org.jstamps.mathlete.functions;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Ceiling {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression(Ceiling.getOperator().getToken() + "(1.5)");
		assertEquals(Double.valueOf(2), Double.valueOf(exp.getSolution()));
	}

}
