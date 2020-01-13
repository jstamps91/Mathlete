package org.jstamps.mathlete.functions;

public interface Function {

	/**
	 * Evaluates the supplied arguments.
	 * 
	 * @param args
	 *            The arguments to evaluate.
	 * @return Returns the solution of an evaluation.
	 */
	double evaluate(double... args);

	/**
	 * Gets the number of arguments used in this function.
	 * 
	 * @return Returns the argument count.
	 */
	int getArgumentCount();

	/**
	 * The symbol of the operation used in an expression.
	 * 
	 * @return Returns the operations symbol.
	 */
	String getToken();
}
