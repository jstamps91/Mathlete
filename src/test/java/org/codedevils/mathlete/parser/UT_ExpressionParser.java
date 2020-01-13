package org.jstamps.mathlete.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class UT_ExpressionParser {

	static class TestParser extends ExpressionParser {
		@Override
		public List<String> tokenize(String expression) {
			return super.tokenize(expression);
		}
		
		@Override
		public List<String> getPostfixNotation(List<String> tokens) {
			return super.getPostfixNotation(tokens);
		}
	}
	
	private static List<String> tokens;
	private static List<String> postfixTokens;
	private static double solution;
	private static String[] components = new String[] { "10.0", "/", "(", "2.0", "+", "3.0", ")", "*", "(", "6.0", "-", "4.0", ")" };
	private static String[] postfixComponents = new String[] { "10.0", "2.0", "3.0", "+", "/", "6.0", "4.0", "-", "*" };
		
	@BeforeClass
	public static void setup() {
		TestParser parser = new TestParser();
		String expression = String.join(" ", components);
		tokens = parser.tokenize(expression);
		postfixTokens = parser.getPostfixNotation(tokens);
		solution = parser.evaluate(expression);
	}
	
	@Test
	public void testTokenize() {
				
		System.out.println("Tokens = " + tokens);
		
		for (int i = 0; i < tokens.size(); i++) {
			assertEquals(components[i], tokens.get(i));
		}		
	}
	
	@Test
	public void testPostfix() {
		System.out.println("Postfix = " + postfixTokens);
		
		for (int i = 0; i < postfixTokens.size(); i++) {
			assertEquals(postfixComponents[i], postfixTokens.get(i));
		}
	}
	
	@Test
	public void testEvaluation() {
		assertEquals(Double.valueOf(4), Double.valueOf(solution));
	}
}
