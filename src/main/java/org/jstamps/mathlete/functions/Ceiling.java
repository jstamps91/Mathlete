package org.jstamps.mathlete.functions;

/**
 * This Function the smallest whole number closest to the argument.
 */
public class Ceiling implements Function {

	private final static int argumentCount = 1;
	private final static String token = "ceil";

	private static Ceiling instance = new Ceiling();

	/**
	 * Initializes a new instance of the Ceiling Function.
	 */
	protected Ceiling() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Ceiling getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		return Math.ceil(x);
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
