package org.jstamps.mathlete.operations;

/**
 * This Operation divides two arguments.
 */
public class Division implements Operation {

	private final static Associativity associativity = Associativity.LEFT;
	private final static int precedence = 3;
	private final static int argumentCount = 2;
	private final static String token = "/";
	private static Division instance = new Division();

	/**
	 * Initializes a new instance of the Division Operation.
	 */
	protected Division() {
		// do nothing here
	}

	/**
	 * Gets the operator instance.
	 */
	public static Division getOperator() {
		return instance;
	}

	@Override
	public double evaluate(double... args) {
		double x = args[0];
		double y = args[1];
		return x / y;
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
