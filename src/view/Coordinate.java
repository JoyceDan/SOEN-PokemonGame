package view;

import java.awt.Color;

public interface Coordinate {
	
	public void refresh();
	public void setBackgroundColor(Color color);
	public void setViewLocation(int x, int y,int width, int height);
	
}
