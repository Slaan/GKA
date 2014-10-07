package interface_view;

import java.awt.event.ActionListener;

public interface BreadthFirstWindow {
	
	public void addStartButtonListener(ActionListener al);
	public void setResultText(String result);
	public String getStartVertex();
	public String getTargetVertex();
}
