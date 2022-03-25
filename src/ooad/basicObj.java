package ooad;

import java.awt.Graphics;

import javax.swing.*;

@SuppressWarnings("serial")
public abstract class basicObj extends JPanel {
	public int x1,x2;
	public int y1,y2;
	public Canvas canvas;
	public abstract void draw(Graphics g);
	basicObj(Canvas canvas){
		canvas.moveToFront(this);
		
		
		
	}
	
}
