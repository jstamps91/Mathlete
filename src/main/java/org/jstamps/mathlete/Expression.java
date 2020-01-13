package org.jstamps.mathlete;

import java.util.List;

import org.jstamps.mathlete.parser.ExpressionParser;

public class Expression {

	protected final String tokenJoiner = "";
	private List<String> infix;
	private List<String> postfix;
	private double solution;
	
	public Expression(List<String> infix, List<String> postfix, double solution) {
		this.infix = infix;
		this.postfix = postfix;
		this.solution = solution;
	}
	
	public Expression(String expression) {
		ExpressionParser parser = new ExpressionParser();
		Expression exp = parser.getExpression(expression);
		this.infix = exp.getInfixNotation();
		this.postfix = exp.getPostfixNotation();
		this.solution = exp.getSolution();
	}
	
	public List<String> getInfixNotation() {
		return infix;
	}
	
	public List<String> getPostfixNotation() {
		return postfix;
	}
	
	@Override
	public String toString() {
		return String.join(tokenJoiner, infix);
	}
	
	public double getSolution() {
		return solution;
	}
}
