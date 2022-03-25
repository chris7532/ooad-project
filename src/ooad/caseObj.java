package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class caseObj extends basicObj {

	caseObj(Canvas canvas, int x1, int y1){
		super(canvas);
		
		this.setVisible(true);
		this.setOpaque(true);
		this.setBackground(Color.black);
		this.x1 = x1;
		this.y1 = y1;
		
		
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.white);
        g2d.drawOval(x1, y1, 70, 50);
	}

}
