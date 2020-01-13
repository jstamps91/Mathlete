package org.jstamps.mathlete.operations;

/**
 * This Operation raises the first argument to the power of the second argument.
 */
public class Power implements Operation {

	private final static Associativity associativity = Associativity.LEFT;
	private final static int precedence = 5;
	private final static int argumentCount = 2;
	private final static String token = "^";
	private static Power instance = new Power();

	/**
	 * Initializes a new instance of the Power Operation.
	 */
	protected Power() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Power getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		double y = args[1];
		return Math.pow(x, y);
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public int getArgumentCount() {
		return argumentCount;
	}
	
	@Override
	public int getPrecedence() {
		return precedence;
	}

	@Override
	public boolean isLeftAssociative() {
		return associativity == Associativity.LEFT;
	}

}
