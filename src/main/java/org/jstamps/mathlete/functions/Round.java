package org.jstamps.mathlete.functions;

/**
 * This Function returns the closest whole number to the argument with ties rounding up.
 */
public class Round implements Function {

	private final static int argumentCount = 1;
	private final static String token = "round";

	private static Round instance = new Round();

	/**
	 * Initializes a new instance of the Ceiling Function.
	 */
	protected Round() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Round getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		return Math.round(x);
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
