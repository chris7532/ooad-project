package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class associationLine extends basicLine{
	
	associationLine(){}
	associationLine(Point start, Point end, Port startp , Port endp){
		this.lineStartPoint = start;
		this.lineEndPoint = end;
		this.lineStartPort = startp;
		this.lineEndPort = endp;
	}
	
	
	
	public void draw(Graphics g) {
		
		g.setColor(Color.white);
		g.drawLine(lineStartPoint.x, lineStartPoint.y, lineEndPoint.x, lineEndPoint.y);
		
	}
}
