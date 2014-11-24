package interface_model;

import java.util.ArrayList;
import java.util.Map;

public interface FloydWarshallModel extends ShortestWayWeighModel {
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return value of field 
	 */
	public Double getDistanceValue(String source, String target);
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param value
	 */
	public void setDistanceValue(String source, String target, Double value);
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public Integer getTransitValue(String source, String target);
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param value
	 */
	public void setTransitValue(String source, String target, Integer value);
	
	/**
	 * 
	 * @return intern vertex position as index
	 */
	public Map<String, Integer> getIndexMap();
	
	/**
	 * 
	 * @return matrix
	 */
	public ArrayList<ArrayList<Double>> getDistanceMatrix();
	

	/**
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> getTransitMatrix();
}
