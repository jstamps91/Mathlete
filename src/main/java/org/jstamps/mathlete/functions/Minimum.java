package org.jstamps.mathlete.functions;

/**
 * This Function returns the smaller of two arguments. 
 */
public class Minimum implements Function{

	private final static int argumentCount = 2;
	private final static String token = "min";

	private static Minimum instance = new Minimum();

	/**
	 * Initializes a new instance of the Minimum Function.
	 */
	protected Minimum() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Minimum getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		double y = args[1];
		return Math.min(x, y);
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
