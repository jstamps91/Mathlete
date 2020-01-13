package org.jstamps.mathlete.generators;

import org.jstamps.mathlete.operations.Addition;
import org.jstamps.mathlete.operations.Division;
import org.jstamps.mathlete.operations.Modulus;
import org.jstamps.mathlete.operations.Multiplication;
import org.jstamps.mathlete.operations.Operation;
import org.jstamps.mathlete.operations.Power;
import org.jstamps.mathlete.operations.Subtraction;

/**
 * The types of operations that can be used in building a math expression.
 */
public enum OperationType {
	ADDITION {
		@Override
		public Operation getOperator() {
			return Addition.getOperator();
		}
	},
	SUBTRACTION {
		@Override
		public Operation getOperator() {
			return Subtraction.getOperator();
		}
	},
	MULTIPLICATION {
		@Override
		public Operation getOperator() {
			return Multiplication.getOperator();
		}
	},
	DIVISION {
		@Override
		public Operation getOperator() {
			return Division.getOperator();
		}
	},
	MODULUS {
		@Override
		public Operation getOperator() {
			return Modulus.getOperator();
		}
	},
	POWER {
		@Override
		public Operation getOperator() {
			return Power.getOperator();
		}
	};

	/**
	 * @return Returns the symbol to use in a math expression.
	 */
	public abstract Operation getOperator();
}
