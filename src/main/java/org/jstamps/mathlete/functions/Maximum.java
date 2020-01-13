package org.jstamps.mathlete.functions;

/**
 * This Function returns the greater of two arguments.
 */
public class Maximum implements Function {

	private final static int argumentCount = 2;
	private final static String token = "max";

	private static Maximum instance = new Maximum();

	/**
	 * Initializes a new instance of the Maximum Function.
	 */
	protected Maximum() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Maximum getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		double y = args[1];
		return Math.max(x, y);
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
