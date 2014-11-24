package interface_model;

import java.util.ArrayList;

public interface ShortestWayWeighModel extends ShortestWayModel {
	
	/**
	 * 
	 * @return weight from last calculated path;
	 */
	public double getWeight();
}
