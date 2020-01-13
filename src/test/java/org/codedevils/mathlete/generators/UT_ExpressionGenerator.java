package org.jstamps.mathlete.generators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.jstamps.mathlete.Expression;
import org.jstamps.mathlete.generators.ExpressionGenerator;
import org.jstamps.mathlete.generators.ExpressionSettings;
import org.jstamps.mathlete.generators.OperationType;
import org.junit.BeforeClass;
import org.junit.Test;

public class UT_ExpressionGenerator {

	private static ExpressionSettings settings;
	private static String[] expressionStrings;
	private static Expression[] expressions;
	private static String delimiters;

	@BeforeClass
	public static void Setup() {
		settings = new ExpressionSettings();
		settings.setTermCount(ThreadLocalRandom.current().nextInt(2, 6));
		settings.setOperationTypes(new OperationType[] { OperationType.ADDITION, OperationType.SUBTRACTION,
				OperationType.MULTIPLICATION, OperationType.DIVISION, OperationType.MODULUS, OperationType.POWER });
		ExpressionGenerator gen = new ExpressionGenerator(settings);
		expressionStrings = gen.generate(10000);
		expressions = gen.buildExpressions(10000);
		List<String> needsEscaping = new ArrayList<>();
		needsEscaping.add("+");
		needsEscaping.add("-");
		needsEscaping.add("*");
		needsEscaping.add("/");
		needsEscaping.add("^");
		needsEscaping.add("%");
		StringBuilder delims = new StringBuilder("[");
		
		for (OperationType ot : settings.getOperationTypes()) {
			String delim = ot.getOperator().getToken();
			
			if (needsEscaping.contains(delim)) {
				delim = "\\" + delim;
			}
			
			delims.append(delim);
		}
				
		delims.append("]");
		delimiters = delims.toString();
	}

	@Test
	public void testTermRange() {
		for (String expression : expressionStrings) {
			String[] terms = expression.split(delimiters);
			for (String term : terms) {
				int value = Integer.parseInt(term);
				assertTrue(value <= settings.getMaxOperand());
				assertTrue(value >= settings.getMinOperand());
			}
		}
	}

	@Test
	public void testTermCount() {
		for (String expression : expressionStrings) {
			String[] terms = expression.split(delimiters);
			System.out.println(expression);
			assertEquals(settings.getTermCount(), terms.length);
		}
	}

	@Test
	public void testOperationTypes() {
		String find = "[^" + delimiters.substring(1);
		List<String> symbols = new ArrayList<>();
		for (OperationType op : settings.getOperationTypes()) {
			symbols.add(op.getOperator().getToken());
		}
		for (String expression : expressionStrings) {
			char[] operations = expression.replaceAll(find, "").toCharArray();
			for (char op : operations) {
				assertTrue(symbols.contains(Character.toString(op)));
			}
		}
	}
	
	@Test
	public void testSolutionRange() {
		for (Expression expression : expressions) {
			System.out.println(expression.toString() + " = " + expression.getSolution());
			assertTrue(expression.getSolution() <= settings.getMaxSolution());
			assertTrue(expression.getSolution() >= settings.getMinSolution());
		}
	}
}
