package impl_model;

import interface_model.NamedEdge;

import org.jgrapht.graph.DefaultWeightedEdge;

public class NamedWeightedEdge extends DefaultWeightedEdge implements NamedEdge {

	@Override
	public String toString() {
		return Double.toString(this.getWeight());
	}

}
