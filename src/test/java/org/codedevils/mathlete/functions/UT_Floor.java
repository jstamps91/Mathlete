package org.jstamps.mathlete.functions;

import static org.junit.Assert.*;

import org.jstamps.mathlete.Expression;
import org.junit.Test;

public class UT_Floor {

	@Test
	public void testEvaluation() {
		Expression exp = new Expression(Floor.getOperator().getToken() + "(1.5)");
		assertEquals(Double.valueOf(1), Double.valueOf(exp.getSolution()));
	}

}
