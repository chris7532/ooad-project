package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class caseObj extends basicObj {

	caseObj(Canvas canvas){
		super(canvas);
		
		portArray[0].setBounds(40, 0, 10, 10);
		portArray[1].setBounds(80, 30, 10, 10);
		portArray[2].setBounds(40, 60, 10, 10);
		portArray[3].setBounds(0, 30, 10, 10);
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
