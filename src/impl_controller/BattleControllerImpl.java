package impl_controller;

import impl_model.GKAModel;
import impl_model.NaechstgelegnerKnotenAlgModelImpl;
import impl_model.NamedWeightedEdge;
import impl_view.BattleWindow;
import interface_controller.BattleController;
import interface_model.GeneratorModel;
import interface_model.MinimalerSpannbaumHeuristikModel;
import interface_model.NaechstgelegnerKnotenAlgModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.jgrapht.Graph;

public class BattleControllerImpl implements BattleController {

	private BattleWindow							_bw;
	private Double									_nka_time_max=0.0;
	private Double									_nka_distance_max=0.0;
	private Double									_msh_time_max;
	private Double									_msh_distance_max;
	private GeneratorModel							_generator;
	
	public static BattleController create() {
		return new BattleControllerImpl();
	}
	
	public BattleControllerImpl() {
		_bw = new BattleWindow();
		_bw.visible();
		_bw.getStartButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startbattle();
			}
		});
	}
	
	public void startbattle() {
		_msh_time_max = 0.0;
		_nka_time_max = 0.0;
		_nka_distance_max = 0.0;
		_msh_distance_max = 0.0;
		
		for (int i=0;i<100;i++) {
			_generator = GKAModel.generator();
			Graph<String,NamedWeightedEdge> graph;
			graph = _generator.genereateCompleteUndirectedGraph(200);
			NaechstgelegnerKnotenAlgModel nka = GKAModel.naechstgelegnerKnotenAlg(graph);
			MinimalerSpannbaumHeuristikModel msh = GKAModel.minimalerSpannbaumHeuristik(graph);
			nka.start("1",null);
			double nka_time = nka.getTime();
			double nka_distance = nka.getWeight();
			_nka_time_max += nka_time;
			_nka_distance_max += nka_distance;	
			
			_bw.setCounter(i+1);
			_bw.setNKAAverageDistance(_nka_distance_max/(i+1));
			_bw.setNKAAverageTime(_nka_time_max/(i+1));
			_bw.setNKATotalDistance(_nka_distance_max);
			_bw.setNKATotalTime(_nka_time_max);
			_bw.update(); 
			
			msh.start("1",null);
			double msh_time = msh.getTime();
			double msh_distance = msh.getWeight();
			_msh_time_max += msh_time;
			_msh_distance_max += msh_distance;
			
			_bw.setMSHAverageDistance(_msh_distance_max/(i+1));
			_bw.setMSHAverageTime(_msh_time_max/(i+1));
			_bw.setMSHTotalDistance(_msh_distance_max);
			_bw.setMSHTotalTime(_msh_time_max);
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					_bw.update();
				}
			});
		}
	}
	
}
