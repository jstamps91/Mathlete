package org.jstamps.mathlete.functions;

/**
 * This Function returns the largest whole number closest to the argument.
 */
public class Floor implements Function {

	private final static int argumentCount = 1;
	private final static String token = "floor";

	private static Floor instance = new Floor();

	/**
	 * Initializes a new instance of the Floor Function.
	 */
	protected Floor() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Floor getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		return Math.floor(x);
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public int getArgumentCount() {
		return argumentCount;
	}
}
