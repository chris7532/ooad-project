package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane implements MouseListener,MouseMotionListener {
	
	
	public Myframe frame;
	private Point drawPoint;
	public Point startPoint;
	public Point dragPoint; 
	public Point endPoint; 
	public int windowWidth;
	public int windowHeight;
	
	public ArrayList<basicObj> basicArray;
	public ArrayList<basicObj>selectedObj;
	Canvas(Myframe frame){
		
		this.setOpaque(true);
		this.setLayout(null);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.DARK_GRAY);
		this.frame = frame;
		basicArray = new ArrayList<basicObj>();
		selectedObj = new ArrayList<basicObj>();
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawWindow(g);
		
		
	}
	public void drawWindow(Graphics g) {
		if(startPoint !=null && dragPoint != null ) {
			Graphics2D g2d = (Graphics2D)g;
			//g2d.drawRect(startPoint, dragPoint, windowWidth, windowHeight);
			Rectangle rect= new Rectangle(startPoint);
			rect.add(dragPoint);
			g2d.setColor(new Color(30,144,255,30));
			g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(frame.currentBtn == frame.classes) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new classObj(this);
			this.add(basic, 0);
			//basic.setLocation(drawPoint.x, drawPoint.y);
			basic.setLocation(drawPoint);
			basicArray.add(basic);
			
			
		}
		if(frame.currentBtn == frame.useCase) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new caseObj(this);
			this.add(basic, 0);
			basic.setLocation(drawPoint);
			basicArray.add(basic);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (frame.currentBtn == frame.sltButton) {
			selectedObj.clear();
			startPoint = e.getPoint();
			System.out.println(startPoint);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (frame.currentBtn == frame.sltButton) {
			endPoint = e.getPoint();
			windowWidth = Math.abs(endPoint.x - startPoint.x);
			windowHeight = Math.abs(endPoint.y - startPoint.y);
			for(basicObj i :basicArray) {
				Point location = i.getLocation();
				if(location.x > startPoint.x && location.x < (startPoint.x + windowWidth) && location.y > startPoint.y && location.y < (endPoint.y + windowHeight)) {
					selectedObj.add(i);
					}
			
			for(basicObj j : selectedObj) {
				j.showPort();
			}
			if(selectedObj.isEmpty()) {
				for(basicObj j : basicArray) {
					j.hidePort();
				}
			}
				
				
			}
		}
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
    public void mouseDragged(MouseEvent e) {
		if (frame.currentBtn == frame.sltButton) {
			dragPoint = e.getPoint();
			repaint();
		}
		
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
