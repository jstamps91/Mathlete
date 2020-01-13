package org.jstamps.mathlete.parser;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The Tokenizer will parse individual tokens form an expression.
 */
public class Tokenizer {

	/**
	 * Tokenizes the supplied expression.
	 * 
	 * @param expression
	 *            The math expression to tokenize.
	 * @return Returns a list of tokens in the supplied expression.
	 */
	public List<String> tokenize(String expression) {
		List<String> tokens = new ArrayList<String>();

		try {
			StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(expression));
			tokenizer.ordinaryChar('/');
			tokenizer.lowerCaseMode(true);

			while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
				switch (tokenizer.ttype) {
				case StreamTokenizer.TT_NUMBER:
					tokens.add(String.valueOf(tokenizer.nval));
					break;
				case StreamTokenizer.TT_WORD:
					tokens.add(tokenizer.sval);
					break;
				default:
					// a single character token is the ttype value
					tokens.add(String.valueOf((char) tokenizer.ttype));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		tokens = fixNegativeOperators(tokens);
		return tokens;
	}

	/**
	 * Looks for adjacent numeric tokens where the second number is negative
	 * then change the number to a positive value and insert a minus operator
	 * between the numeric tokens.
	 * 
	 * @param tokens
	 *            The tokens to check.
	 * @return Returns tokens with corrected negative values.
	 */
	protected List<String> fixNegativeOperators(List<String> tokens) {
		for (int i = 0; i < tokens.size() - 1; i++) {
			String leftToken = tokens.get(i);
			String rightToken = tokens.get(i + 1);

			if (isNumber(leftToken) && isNumber(rightToken)) {
				// This value is negative!
				if (rightToken.startsWith("-")) {
					// Strip the minus sign
					rightToken = rightToken.substring(1);

					// Replace current right side token with the positive
					// equivalent
					tokens.set(i + 1, rightToken);

					// Add the minus sign between the tokens
					tokens.add(i + 1, "-");
				}
			}
		}

		return tokens;
	}

	protected boolean isNumber(String token) {
		try {
			Double.parseDouble(token);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
}
