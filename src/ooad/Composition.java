package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Composition extends JPanel implements MouseListener, MouseMotionListener{

	
	public int xMin = 10000, xMax = 0, yMin = 10000, yMax = 0;
	public int offsetMin = 30;
	public int offsetMax = 120 + offsetMin;
	public Composition parentComp;
	public Canvas canvas;
	
    public ArrayList<basicObj> subBlock;
    public ArrayList<Composition> subComposite;
	
	Composition(Canvas canvas){
		
		this.canvas = canvas;
		findBounds();
		this.setSize(xMax-xMin, yMax-yMin);
		this.setLocation(xMin, yMin);
		this.setBorder(BorderFactory.createLineBorder(new Color(255,228,181,200), 3));
		this.setBackground(Color.BLACK);	
		//this.setBackground(new Color(255,228,196,30));
		parentComp = null;
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
	
	
	public void findBounds() {
		
		for(basicObj obj :canvas.selectedObj) {
			if(obj.getLocation().x < xMin) {
				xMin = obj.getLocation().x;
			}
			if(obj.getLocation().x > xMax) {
				xMax = obj.getLocation().x;
			}
			if(obj.getLocation().y < yMin) {
				yMin = obj.getLocation().y;
			}
			if(obj.getLocation().y > yMax) {
				yMax = obj.getLocation().y;
			}
			this.xMin -= offsetMin;
			this.xMax += offsetMax;
			this.yMin -= offsetMin;
			this.yMax += offsetMax;
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(canvas.frame.currentBtn == canvas.frame.sltButton) {
			
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
