package impl_model;

import interface_model.GeneratorModel;

import java.util.ArrayList;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.WeightedPseudograph;

class GeneratorModelImpl implements GeneratorModel {

	private final int 			WEIGHT_SPREAD=50;
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
			Integer v_int1 = generator.nextInt(vertexes)+1;
			Integer v_int2 = generator.nextInt(vertexes)+1;
			edge = result.addEdge(v_int1.toString(), v_int2.toString());
			Integer weight = generator.nextInt(19)+1;
			edge.setWeight(Double.valueOf(weight));
			
			System.out.println(a++);
		}		
		return result;
	}
	
	@Override
	public Graph<String,NamedWeightedEdge> genereateCompleteUndirectedGraph(int vertexes) {
		WeightedPseudograph<String, NamedWeightedEdge> result = new WeightedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> vertex_list = new ArrayList<>();
		for (Integer i=1;i<=vertexes;vertex_list.add(""+i),result.addVertex(""+i++));
		Random generator = new Random();
		int position = 1;
		for (String s1 : vertex_list) {
			for (int i=position; i<vertexes;i++) {
				NamedWeightedEdge edge = new NamedWeightedEdge();
				edge.setWeight(generator.nextInt(WEIGHT_SPREAD)+(WEIGHT_SPREAD-1.0));
				result.addEdge(s1, vertex_list.get(i), edge);
			}
		position++;
		}
			
		return result;
	}
}
