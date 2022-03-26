package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class caseObj extends basicObj {

	caseObj(Canvas canvas){
		super(canvas);
		
		p1.setBounds(40, 0, 10, 10);
		p2.setBounds(80, 30, 10, 10);
		p3.setBounds(40, 60, 10, 10);
		p4.setBounds(0, 30, 10, 10);
		this.setSize(90,70);
		
		
			
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
        g2d.drawOval(10, 10, 70, 50);
	}

}
