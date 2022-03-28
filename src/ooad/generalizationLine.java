package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;

public class generalizationLine extends basicLine{
	
	generalizationLine(){}
	generalizationLine(Point start, Point end, Port startp , Port endp){
		this.lineStartPoint = start;
		this.lineEndPoint = end;
		this.lineStartPort = startp;
		this.lineEndPort = endp;
	}
	
	
	
	
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
        double H = 12;
        double L = 8;
        double sx = lineStartPoint.getX(); 
        double sy = lineStartPoint.getY(); 
        double ex = lineEndPoint.getX();
        double ey = lineEndPoint.getY();
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];
        
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x_3, y_3);
        triangle.lineTo(x_4, y_4);
        triangle.closePath();
        
        g2d.draw(triangle);
        g2d.drawLine((int) sx, (int) sy, (int) (x_3 + (x_4 - x_3) / 2), (int) (y_3 + (y_4 - y_3) / 2));
        
        
	}
	
	public static double[] rotateVec(double e, double f, double ang,
            boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        double vx = e * Math.cos(ang) - f * Math.sin(ang);
        double vy = e * Math.sin(ang) + f * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }
	
}

