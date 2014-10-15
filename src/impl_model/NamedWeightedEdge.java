package impl_model;

import org.jgrapht.graph.DefaultEdge;

/**
 * This content has been copied from:
 * @author Torben Haug, Jana Wengenroth
 * and beed edited by
 * @author Alex and Daniel
 */
public class NamedWeightedEdge extends DefaultEdge {

	/**
	 * 
	 */
	private String _name = null;
	private Double _weight = null;
	
	public NamedWeightedEdge(){
		super();
	}
	public Object getSource(){
		return super.getSource();
	}
	
	public Object getTarget(){
		return super.getTarget();
	}
	
	public Double getWeight() {
		return _weight;
	}
	
	public void setName(String name) {
		if(name == null) throw new NullPointerException();
		_name = name;
	}
	
	public void setWeight(Double weight) {
		if(weight == null) throw new NullPointerException();
		_weight = weight;
	}
	
	public String getName(){
		return this._name;
	}
	
    public String toString() {
    	String retVal;
    	if (_name == null){
    		retVal = "(" + getSource() + " : " + getTarget() + ")";
    	}
    	else{
    		retVal = "(" + _name + ")";
    	}
    	if (getWeight() != null){
    		retVal += " : " + getWeight();
    	}
    	return retVal;
    }
    
    @Override
    public boolean equals(Object object) {
        if(object == null){
            return false;
        }
        else if(object == this){
            return true;
        }
        else if(!(object instanceof NamedWeightedEdge)){
            return false;
        }
        else{
            NamedWeightedEdge edge = (NamedWeightedEdge) object;
            boolean retval = true;
            if(getSource() == null){
            	retval = retval && edge.getSource() == null;
            }
            else{
            	retval = retval && getSource().equals(edge.getSource());
            }

            if(getTarget() == null){
            	retval = retval && edge.getTarget() == null;
            }
            else{
            	retval = retval && getTarget().equals(edge.getTarget());
            }
            
            if(getName() == null){
            	retval = retval && edge.getName() == null;
            }
            else{
            	retval = retval && getName().equals(edge.getName());
            }
            
            if(getWeight() == null){
            	retval = retval && edge.getWeight() == null;
            }
            else{
            	retval = retval && getWeight().equals(edge.getWeight());
            }
            return retval;
        }
    }
    
    @Override
    public int hashCode() {
    	int retVal = 31;
    	if(getSource() != null){
    		retVal += getSource().hashCode();
        }
    	if(getTarget() != null){
    		retVal += getTarget().hashCode();
        }
    	if(getName() != null){
    		retVal += getName().hashCode();
        }
    	if(getWeight() != null){
    		retVal += getWeight().hashCode();
        }
        return retVal;
    }
}
