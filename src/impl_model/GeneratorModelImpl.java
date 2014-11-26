package impl_model;

import interface_model.GeneratorModel;

import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

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
		for (Integer i=1;i<=vertexes;result.addVertex(""+i++));
		Random generator = new Random();
		int a = 1;
		for (int i=0; i<edges; i++) {
			NamedWeightedEdge edge;
			Integer v_int1 = generator.nextInt(vertexes-1)+1;
			Integer v_int2 = generator.nextInt(vertexes-1)+1;
			edge = result.addEdge(v_int1.toString(), v_int2.toString());
			Integer weight = generator.nextInt(19)+1;
			edge.setWeight(Double.valueOf(weight));
			
			System.out.println(a++);
		}		
		return result;
	}
}
