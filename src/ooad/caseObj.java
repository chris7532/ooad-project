package ooad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class caseObj extends basicObj {

	boolean firstDraw = true;

	caseObj(Canvas canvas) {
		super(canvas);

		portArray[0].setBounds(40, 0, 10, 10);
		portArray[1].setBounds(80, 30, 10, 10);
		portArray[2].setBounds(40, 60, 10, 10);
		portArray[3].setBounds(0, 30, 10, 10);
		this.setSize(90, 70);

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.drawOval(10, 10, 70, 50);
		int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (70 - stringWidth) / 2;
		font = new Font(Font.DIALOG, Font.PLAIN, 11);
		g.setFont(font);
		if (firstDraw) {
			g.drawString(objectName, 23 + (int) empty, 13 + 25);
			this.firstDraw = false;
		} else
			g.drawString(objectName, 10 + (int) empty, 13 + 25);
	}

}
