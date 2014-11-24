package interface_model;

public interface AlgorithmModel {

	/**
	 * @return Accesses on graph during last algorithm run. By default 0.
	 */
	public int getGraphAccesses();
	
	/**
	 * 
	 * @return amount of accesses on graph during all runs.
	 */
	public int getTotalGraphAccesses();
	
	/**
	 * @return Time used in milliseconds. By default 0.
	 */
	public double getTime();
}
