package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class classObj extends basicObj{
	
	classObj(Canvas canvas, int x1, int y1){
		super(canvas);
		
		this.setVisible(true);
		this.setOpaque(true);
		this.setBackground(Color.black);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + 100;
		this.y2 = y1 + 120;
		
		
	}
	public void draw(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
		g2d.setPaint(Color.white);
		System.out.println(x1);
		g2d.drawRect(x1, y1, 100, 120);
		int portion = 40;
		g2d.drawLine(x1, y1 + portion, x2, y1 + portion);
		g2d.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);
		
	}	
}
