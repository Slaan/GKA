package interface_view;

import java.awt.event.ActionListener;

public interface BreadthFirstWindow {
	
	public String getStartVertex();
	public String getTargetVertex();
	public void setGraphAccesses(int hops);
	public void setResultText(String result);
	public void addStartButtonListener(ActionListener al);
}
