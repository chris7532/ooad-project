package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class classObj extends basicObj{
	Canvas canvas;
	classObj(Canvas canvas){
		super(canvas);
		this.canvas = canvas;
		p1.setBounds(55, 0, 10, 10);
		p2.setBounds(110,65, 10, 10);
		p3.setBounds(55, 130, 10, 10);
		p4.setBounds(0, 65, 10, 10);
		
		this.setSize(120,140);
		//this.setLocation(x1, y1);
		//this.setBackground(Color.white);
		
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
        g.setColor(Color.white);
        g.drawRect(10, 10, 100, 120);
        g.drawLine(10, 50, 110, 50);
        g.drawLine(10, 90, 110, 90);
        
        
        
        
		/*
		int portion = 40;
		g.setColor(Color.black);
		g.drawRect(x1, y1, 100, 120);
		g.drawLine(x1, y1 + portion, x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);*/
		
	}	
	
}
