package interface_view;

import java.awt.event.ActionListener;

public interface BreadthFirstWindow {
	
	public void addStartButtonListener(ActionListener al);
	public String getStartVertex();
	public String getTargetVertex();
	public void setResultText(String result);
}
