package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class classObj extends basicObj {
	Canvas canvas;

	classObj(Canvas canvas) {
		super(canvas);
		this.canvas = canvas;
		portArray[0].setBounds(55, 0, 10, 10);
		portArray[1].setBounds(110, 65, 10, 10);
		portArray[2].setBounds(55, 130, 10, 10);
		portArray[3].setBounds(0, 65, 10, 10);

		this.setSize(120, 140);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.drawRect(10, 10, 100, 120);
		g.drawLine(10, 50, 110, 50);
		g.drawLine(10, 90, 110, 90);

		int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (60 - stringWidth) / 2;
		g.setFont(font);
		g.drawString(objectName, 30 + (int) empty, 10 + 25);


	}

}
