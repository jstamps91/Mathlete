package org.jstamps.mathlete.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class UT_Tokenizer {

	@Test
	public void testTokensComposition() {
		String[] components = new String[] { "1.23", "+", "-4.56", "-", "7.89" };
		String expression = String.join("", components);
		Tokenizer tokenizer = new Tokenizer();
		List<String> tokens = tokenizer.tokenize(expression);
		System.out.println("Tokens = " + tokens);

		for (int i = 0; i < tokens.size(); i++) {
			assertEquals(components[i], tokens.get(i));
		}
	}

}
