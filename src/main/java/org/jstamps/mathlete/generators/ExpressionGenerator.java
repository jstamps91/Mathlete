package org.jstamps.mathlete.generators;

import java.util.concurrent.ThreadLocalRandom;

import org.jstamps.mathlete.Expression;
import org.jstamps.mathlete.parser.ExpressionParser;

/**
 * The ExpressionGenerator will generate math expressions as a string according
 * to the constraints in the ExpressionsSettings value.
 */
public class ExpressionGenerator {

	private ExpressionSettings settings;
	private ThreadLocalRandom randomizer = ThreadLocalRandom.current();

	/**
	 * Initializes a new instance of ExpressionGenerator.
	 * 
	 * @param settings
	 *            The constraints used to generate math expressions.
	 */
	public ExpressionGenerator(ExpressionSettings settings) {
		this.settings = settings;
	}

	/**
	 * Generates math expressions.
	 * 
	 * @param expressionCount
	 *            The total expressions to create.
	 * @return Returns a string array of generated expressions.
	 */
	public String[] generate(int expressionCount) {
		String[] expressions = new String[expressionCount];
		ExpressionParser parser = new ExpressionParser();

		for (int i = 0; i < expressionCount; i++) {
			String expression = buildExpression(settings);
			double solution = parser.evaluate(expression);

			while (solution > settings.getMaxSolution() || solution < settings.getMinSolution()) {
				expression = buildExpression(settings);
				solution = parser.evaluate(expression);
			}

			expressions[i] = expression;
		}

		return expressions;
	}

	/**
	 * Builds a group of math expressions based on the supplied
	 * ExpressionSettings.
	 * 
	 * @param expressionCount
	 *            The number of expressions to build.
	 * @return Returns an array of Expression.
	 */
	public Expression[] buildExpressions(int expressionCount) {
		String[] expressionValues = generate(expressionCount);
		Expression[] expressions = new Expression[expressionCount];

		for (int i = 0; i < expressionCount; i++) {
			String value = expressionValues[i];
			expressions[i] = new Expression(value);
		}

		return expressions;
	}

	/**
	 * Builds a single math expression.
	 * 
	 * @param settings
	 *            The constraints used to generate math expressions.
	 * @return Returns a single math expression.
	 */
	protected String buildExpression(ExpressionSettings settings) {
		StringBuilder sb = new StringBuilder();
		int term = getTerm(settings);
		sb.append(term);

		for (int i = 1; i < settings.getTermCount(); i++) {
			int index = randomizer.nextInt(settings.getOperationTypes().length);
			String operator = settings.getOperationTypes()[index].getOperator().getToken();
			term = getTerm(settings);
			sb.append(operator);
			sb.append(term);
		}

		return sb.toString();
	}

	/**
	 * Generates a term between the min and max opperand constraints.
	 * 
	 * @param settings
	 *            The constraints used to generate math expressions.
	 * @return Returns a single term to use in building an expression.
	 */
	protected int getTerm(ExpressionSettings settings) {
		return randomizer.nextInt(settings.getMinOperand(), settings.getMaxOperand() + 1);
	}
}
