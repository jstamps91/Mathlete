package org.jstamps.mathlete.functions;

/**
 * This Function returns the absolute value of the argument.
 */
public class AbsoluteValue implements Function {

	private final static int argumentCount = 1;
	private final static String token = "abs";

	private static AbsoluteValue instance = new AbsoluteValue();

	/**
	 * Initializes a new instance of the AbsoluteValue Function.
	 */
	protected AbsoluteValue() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static AbsoluteValue getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		return Math.abs(x);
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
