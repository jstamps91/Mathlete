/**
 * 
 */
package org.jstamps.mathlete.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.jstamps.mathlete.Expression;
import org.jstamps.mathlete.functions.AbsoluteValue;
import org.jstamps.mathlete.functions.Ceiling;
import org.jstamps.mathlete.functions.Floor;
import org.jstamps.mathlete.functions.Function;
import org.jstamps.mathlete.functions.Maximum;
import org.jstamps.mathlete.functions.Minimum;
import org.jstamps.mathlete.functions.Round;
import org.jstamps.mathlete.operations.Addition;
import org.jstamps.mathlete.operations.Division;
import org.jstamps.mathlete.operations.Modulus;
import org.jstamps.mathlete.operations.Multiplication;
import org.jstamps.mathlete.operations.Operation;
import org.jstamps.mathlete.operations.Power;
import org.jstamps.mathlete.operations.Subtraction;

/**
 * Parses a math expression.
 */
public class ExpressionParser {

	private static final String FUNCTION_ARGUMENT_SEPARATOR = ",";
	private static final String START_GROUP = "(";
	private static final String END_GROUP = ")";
	private static final Map<String, Function> supportedFunctions = new HashMap<>();
	private static final Map<String, Operation> supportedOperations = new HashMap<>();
	private static final Map<String, Double> supportedConstants = new HashMap<>();
	protected Tokenizer tokenizer = new Tokenizer();

	static {
		// supported functions
		supportedFunctions.put(AbsoluteValue.getOperator().getToken(), AbsoluteValue.getOperator());
		supportedFunctions.put(Ceiling.getOperator().getToken(), Ceiling.getOperator());
		supportedFunctions.put(Floor.getOperator().getToken(), Floor.getOperator());
		supportedFunctions.put(Maximum.getOperator().getToken(), Maximum.getOperator());
		supportedFunctions.put(Minimum.getOperator().getToken(), Minimum.getOperator());
		supportedFunctions.put(Round.getOperator().getToken(), Round.getOperator());
		// supported operations
		supportedOperations.put(Addition.getOperator().getToken(), Addition.getOperator());
		supportedOperations.put(Subtraction.getOperator().getToken(), Subtraction.getOperator());
		supportedOperations.put(Multiplication.getOperator().getToken(), Multiplication.getOperator());
		supportedOperations.put(Division.getOperator().getToken(), Division.getOperator());
		supportedOperations.put(Modulus.getOperator().getToken(), Modulus.getOperator());
		supportedOperations.put(Power.getOperator().getToken(), Power.getOperator());
		// supported constants
		supportedConstants.put("pi", Math.PI);
		supportedConstants.put("e", Math.E);
	}

	/**
	 * Evaluates the solution of a math expression.
	 * 
	 * @param expression
	 *            The math expression to evaluate.
	 * @return Returns the expression solution.
	 */
	public double evaluate(String expression) {
		List<String> tokens = tokenize(expression);
		List<String> postfix = getPostfixNotation(tokens);
		double solution = evaluate(postfix);
		return solution;
	}

	/**
	 * Parses a String expression into an Expression object.
	 * 
	 * @param expression
	 *            The String to parse.
	 * @return Returns an Expression object.
	 */
	public Expression getExpression(String expression) {
		List<String> tokens = tokenize(expression);
		List<String> postfix = getPostfixNotation(tokens);
		double solution = evaluate(postfix);
		return new Expression(tokens, postfix, solution);
	}

	/**
	 * Parses the tokens of a math expression.
	 * 
	 * @param expression
	 *            A math expression.
	 * @return Returns a math expression as tokens.
	 */
	protected List<String> tokenize(String expression) {
		List<String> tokens = tokenizer.tokenize(expression);

		for (int i = 0; i < tokens.size() - 1; i++) {
			String leftToken = tokens.get(i);
			String rightToken = tokens.get(i + 1);

			if (isNumber(leftToken) && isNumber(rightToken)) {
				throw new RuntimeException("Invalid Expression: Sequential opperands " + leftToken + " " + rightToken);
			}
		}

		return tokens;
	}

	/**
	 * Puts an expression in postfix notation.
	 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
	 * 
	 * @param tokens
	 *            The tokens of a math expression.
	 * @return Returns the tokens in postfix notation.
	 */
	protected List<String> getPostfixNotation(List<String> tokens) {
		List<String> outputQueue = new LinkedList<>();
		Stack<String> stack = new Stack<>();

		for (String token : tokens) {
			if (isFunction(token)) {
				stack.push(token);
			} else if (isNumber(token) || isConstant(token)) {
				outputQueue.add(token);
			} else if (isOperation(token)) {
				Operation op1 = getOperation(token);

				// while there is an operator token op2 at the top of the stack
				// and either token is left associative and token precedence <=
				// op2 or token has less precedence than op2
				// then pop op2 off the stack onto the output queue
				while (!stack.isEmpty() && isOperation(stack.peek())) {
					Operation op2 = getOperation(stack.peek());
					boolean op1HasLessPrecedence = op1.getPrecedence() < op2.getPrecedence();
					boolean op1IsLeftAssocAndHasPrecedenceLTE = (op1.isLeftAssociative()
							&& op1.getPrecedence() <= op2.getPrecedence());

					if (op1HasLessPrecedence || op1IsLeftAssocAndHasPrecedenceLTE) {
						String op2Token = stack.pop();
						outputQueue.add(op2Token);
					} else {
						break;
					}
				}

				stack.push(token);
			} else if (token.equals(FUNCTION_ARGUMENT_SEPARATOR)) {
				// until the token at the top of the stack is an START_GROUP
				// pop operators off the stack onto the output queue
				while (!stack.peek().equals(START_GROUP)) {
					String stackToken = stack.pop();
					outputQueue.add(stackToken);

					if (stack.isEmpty()) {
						throw new RuntimeException("Mismatched parentheses or comma detected in expression.");
					}
				}
			} else if (token.equals(START_GROUP)) {
				stack.push(token);
			} else if (token.equals(END_GROUP)) {
				// until the token at the top of the stack is a START_GROUP
				// pop operators off the stack onto the output queue
				// pop the START_GROUP from the stack, but not onto the output
				// queue
				// if the token at the top of th estack is a function token
				while (!stack.peek().equals(START_GROUP)) {
					String stackToken = stack.pop();
					outputQueue.add(stackToken);

					if (stack.isEmpty()) {
						throw new RuntimeException("Mismatched parentheses detected in expression.");
					}
				}

				// pop the top element off the stack
				// which should be a START_GROUP token
				stack.pop();

				if (!stack.isEmpty() && isFunction(stack.peek())) {
					String stackToken = stack.pop();
					outputQueue.add(stackToken);
				}
			}
		}

		while (!stack.isEmpty()) {
			String stackToken = stack.pop();

			if (isGroupingOperator(stackToken)) {
				throw new RuntimeException("Mismatched parentheses detected in expression.");
			}

			outputQueue.add(stackToken);
		}

		return outputQueue;
	}

	/**
	 * Evaluates the solution to a math expression in postfix notation.
	 * 
	 * @param postfixTokens
	 *            The tokens of an expression in postfix notation.
	 * @return Returns the expression solution.
	 */
	protected double evaluate(List<String> postfixTokens) {
		LinkedList<Double> arguments = new LinkedList<>();

		for (String token : postfixTokens) {
			Function function = null;

			if (isFunction(token)) {
				function = getFunction(token);
			} else if (isOperation(token)) {
				function = getOperation(token);
			} else if (isNumber(token)) {
				arguments.push(getNumber(token));
			} else if (isConstant(token)) {
				arguments.push(getConstant(token));
			} else {
				throw new RuntimeException("Invalid token in expression (" + token + ").");
			}

			if (function != null) {
				// get arguments for function in reverse order
				int argCount = function.getArgumentCount();
				double[] args = new double[argCount];

				for (int i = argCount - 1; i >= 0; i--) {
					args[i] = arguments.pop();
				}

				double solution = function.evaluate(args);
				arguments.push(solution);
			}
		}

		if (arguments.size() > 1) {
			throw new RuntimeException("Invalid expression: unable to evaluate the expression solution.");
		}

		return arguments.pop();
	}

	/**
	 * Tests if a token is a number.
	 * 
	 * @param token
	 *            The token to test.
	 * @return Returns true if the token is a number otherwise false.
	 */
	protected boolean isNumber(String token) {
		try {
			Double.parseDouble(token);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the token as a number.
	 */
	protected double getNumber(String token) {
		return Double.parseDouble(token);
	}

	/**
	 * Tests if the token is a supported function.
	 * 
	 * @param token
	 *            The token to test.
	 * @return Returns true if the token is a function otherwise false.
	 */
	protected boolean isFunction(String token) {
		return supportedFunctions.containsKey(token);
	}

	/**
	 * Gets a supported function.
	 */
	public Function getFunction(String token) {
		return supportedFunctions.get(token);
	}

	/**
	 * Tests if the token is a supported operation.
	 * 
	 * @param token
	 *            The token to test.
	 * @return Returns true if the token is an operation otherwise false.
	 */
	protected boolean isOperation(String token) {
		return supportedOperations.containsKey(token);
	}

	/**
	 * Gets a supported operation.
	 */
	protected Operation getOperation(String token) {
		return supportedOperations.get(token);
	}

	/**
	 * Tests if the token is a supported constant.
	 * 
	 * @param token
	 *            The token to test.
	 * @return Returns true if the token is a constant otherwise false.
	 */
	protected boolean isConstant(String token) {
		return supportedConstants.containsKey(token);
	}

	/**
	 * Gets a supported constant.
	 */
	protected Double getConstant(String token) {
		return supportedConstants.get(token);
	}

	/**
	 * Tests if the token is a grouping token.
	 * 
	 * @param token
	 *            The token to test.
	 * @return Returns true if the token is a group marker otherwise false.
	 */
	protected boolean isGroupingOperator(String token) {
		return token.matches("[" + START_GROUP + END_GROUP + "]");
	}
}
