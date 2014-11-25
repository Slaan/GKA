package interface_model;

public interface NetworkModel extends AlgorithmModel {

	/**
	 * 
	 * @param source bzw. quelle
	 * @param target bzw. senke
	 * @return max flow
	 */
	public double start(String source, String target);
}
