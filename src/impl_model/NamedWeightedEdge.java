package impl_model;

import org.jgrapht.graph.DefaultWeightedEdge;

public class NamedWeightedEdge extends DefaultWeightedEdge {

	@Override
	public String toString() {
		return Double.toString(this.getWeight());
	}
	
	public String getSource() {
		return (String) super.getSource();
	}

	public String getTarget() {
		return (String) super.getTarget();
	}
}
