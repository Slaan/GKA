package interface_view;

import java.awt.event.ActionListener;

public interface AlgorithmWindow {
	
	public String getStartVertex();
	public String getTargetVertex();
	public void setGraphAccesses(int hops);
	public void setResultText(String result);
	public void setTime(Double time);
	public void addStartButtonListener(ActionListener al);
}
