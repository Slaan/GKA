package impl_model;

import java.util.ArrayList;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import interface_model.GeneratorModel;

class GeneratorModelImpl implements GeneratorModel {

	// Creation
	public static GeneratorModel create() {
		return new GeneratorModelImpl();
	}
	
	private GeneratorModelImpl() {
		// nop
	}
	
	@Override
	public Graph<String, NamedWeightedEdge> generateDirectedGraph(int vertexes, int edges) {
		DirectedWeightedPseudograph<String, NamedWeightedEdge> result = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<Integer> range = makeSequence(0, vertexes-1);
		for (Integer i :range) {
			result.addVertex(i.toString());
		}
		for (int i=0; i<edges; i++) {
			NamedWeightedEdge edge;
			Random generator = new Random();
			Integer v_int1 = generator.nextInt(vertexes-1)+1;
			Integer v_int2 = generator.nextInt(vertexes-1)+1;
			edge = result.addEdge(v_int1.toString(), v_int2.toString());
			Integer weight = generator.nextInt(19)+1;
			edge.setWeight(Double.valueOf(weight));
		}		
		return result;
	}
	
	private ArrayList<Integer> makeSequence(int begin, int end) {
		  ArrayList<Integer> ret = new ArrayList<>(end-begin+1);
		  for(int i = begin; i <= end; i++, ret.add(i));
		  return ret;  
	}

}
