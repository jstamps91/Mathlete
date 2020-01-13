package org.jstamps.mathlete.operations;

import org.jstamps.mathlete.functions.Function;

public interface Operation extends Function {

	/**
	 * The precedence of an operation where a high number indicates higher
	 * precedence.
	 * 
	 * @return Returns the integer value of the operations precedence.
	 */
	int getPrecedence();

	/**
	 * Tests if the operation is left associative.
	 * 
	 * @return Returns true if left associative otherwise false.
	 */
	boolean isLeftAssociative();
}
