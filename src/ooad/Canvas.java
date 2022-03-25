package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane implements MouseListener {
	
	
	public Myframe frame;
	private Point drawPoint;
	
	public ArrayList<basicObj> basicArray;
	
	Canvas(Myframe frame){
		
		this.setOpaque(true);
		this.setLayout(null);
		this.addMouseListener(this);
		this.frame = frame;
		basicArray = new ArrayList<basicObj>();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(basicArray != null) {
			for(basicObj i : basicArray ) {
				
				i.draw(g);
			
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(frame.currentBtn == frame.classes) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new classObj(this,drawPoint.x, drawPoint.y);
			this.add(basic,0);
			basicArray.add(basic);
			this.repaint();
		}
		if(frame.currentBtn == frame.useCase) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new caseObj(this,drawPoint.x, drawPoint.y);
			this.add(basic,0);
			basicArray.add(basic);
			this.repaint();
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
