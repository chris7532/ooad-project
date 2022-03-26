package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class basicObj extends JPanel implements MouseListener, MouseMotionListener {
	
	public int x1,x2;
	public int y1,y2;
	boolean selected;
	public Canvas canvas;
	public int depth;
	Port p1;
	Port p2;
	Port p3;
	Port p4;
	
	public void draw(Graphics g) {}
	
	basicObj(Canvas canvas){
		
		this.canvas = canvas;
		p1 = new Port(this); //N,E,S,W
		p2 = new Port(this);
		p3 = new Port(this);
		p4 = new Port(this);
		this.setLayout(null);
		this.addMouseListener(this);
        this.addMouseMotionListener(this);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		hidePort();
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);
		//this.setBackground(Color.gray);
		//depth = canvas.basicArray.isEmpty()? 99 : 99-canvas.basicArray.size();
		
		//canvas.moveToFront(this);
	}
	public void hidePort() {
		p1.setVisible(false);
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		
	}
	public void showPort() {
		p1.setVisible(true);
		p2.setVisible(true);
		p3.setVisible(true);
		p4.setVisible(true);
		
	}
	
	@Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(canvas.frame.currentBtn == canvas.frame.sltButton) {
        	this.showPort();
        	this.selected = true;
        	canvas.selectedObj.add(this);
        	
        	
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
	
}
