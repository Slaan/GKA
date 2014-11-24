package interface_model;

public interface NetworkModel extends AlgorithmModel {

	/**
	 * 
	 * @param source
	 * @param target
	 * @return max flow
	 */
	public double start(String source, String target);
}
