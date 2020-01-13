package org.jstamps.mathlete.generators;

/**
 * ExpressionSettings are the constraints used to build a math expression.
 */
public class ExpressionSettings {

	private int termCount = 2;
	private OperationType[] operationTypes = new OperationType[] { OperationType.ADDITION };
	private int minOperand = 1;
	private int maxOperand = 9;
	private int minSolution = 1;
	private int maxSolution = 9;

	/**
	 * @return the termCount
	 */
	public int getTermCount() {
		return termCount;
	}

	/**
	 * @param termCount
	 *            the termCount to set
	 */
	public void setTermCount(int termCount) {
		this.termCount = termCount;
	}

	/**
	 * @return the operationTypes
	 */
	public OperationType[] getOperationTypes() {
		return operationTypes;
	}

	/**
	 * @param operationTypes
	 *            the operationTypes to set
	 */
	public void setOperationTypes(OperationType[] operationTypes) {
		this.operationTypes = operationTypes;
	}

	/**
	 * @return the minOperand
	 */
	public int getMinOperand() {
		return minOperand;
	}

	/**
	 * @param minOperand
	 *            the minOperand to set
	 */
	public void setMinOperand(int minOpperand) {
		this.minOperand = minOpperand;
	}

	/**
	 * @return the maxOperand
	 */
	public int getMaxOperand() {
		return maxOperand;
	}

	/**
	 * @param maxOperand
	 *            the maxOperand to set
	 */
	public void setMaxOperand(int maxOperand) {
		this.maxOperand = maxOperand;
	}

	/**
	 * @return the minSolution
	 */
	public int getMinSolution() {
		return minSolution;
	}

	/**
	 * @param minSolution
	 *            the minSolution to set
	 */
	public void setMinSolution(int minSolution) {
		this.minSolution = minSolution;
	}

	/**
	 * @return the maxSolution
	 */
	public int getMaxSolution() {
		return maxSolution;
	}

	/**
	 * @param maxSolution
	 *            the maxSolution to set
	 */
	public void setMaxSolution(int maxSolution) {
		this.maxSolution = maxSolution;
	}
}
