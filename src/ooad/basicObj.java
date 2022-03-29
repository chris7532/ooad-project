package ooad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class basicObj extends JPanel implements MouseListener, MouseMotionListener {

	boolean selected;
	boolean lineConnected;
	public Canvas canvas;
	public int depth;
	public Point objStart;
	public Point objDrag;

	protected String objectName = "Object Name";
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);

	Port[] portArray;
	double[] distanceToPort;
	public associationLine assLine;
	public generalizationLine genLine;
	public compositionLine comLine;

	public int startPortNumber, endPortNumber;

	public Composition parentComp;
	public double parentDistanceX, parentDistanceY;
	public Point moveStartPoint;

	basicObj(Canvas canvas) {

		this.canvas = canvas;

		portArray = new Port[4];
		portArray[0] = new Port(this);// N,E,S,W
		portArray[1] = new Port(this);
		portArray[2] = new Port(this);
		portArray[3] = new Port(this);
		distanceToPort = new double[4];
		this.setLayout(null);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.add(portArray[0]);
		this.add(portArray[1]);
		this.add(portArray[2]);
		this.add(portArray[3]);
		hidePort();
		this.selected = false;
		this.lineConnected = false;
		this.setBackground(Color.DARK_GRAY);
		this.setOpaque(true);

		// this.setBackground(Color.gray);
		// depth = canvas.basicArray.isEmpty()? 99 : 99-canvas.basicArray.size();

		// canvas.moveToFront(this);
	}

	public void hidePort() {
		portArray[0].setVisible(false);
		portArray[1].setVisible(false);
		portArray[2].setVisible(false);
		portArray[3].setVisible(false);

	}

	public void showPort() {
		portArray[0].setVisible(true);
		portArray[1].setVisible(true);
		portArray[2].setVisible(true);
		portArray[3].setVisible(true);

	}

	public void showOne(basicObj obj) {
		for (basicObj i : canvas.basicArray) {
			i.hidePort();
			i.selected = false;
		}
		obj.showPort();
	}

	public void chooseStartPort(MouseEvent e) {
		Point startPoint = e.getPoint(); // relative to basicObj panel
		for (int j = 0; j < 4; j++) {
			distanceToPort[j] = Math.sqrt(Math.pow(portArray[j].getLocation().x - startPoint.x, 2)
					+ Math.pow(portArray[j].getLocation().y - startPoint.y, 2));
		}
		double min = 10000;
		for (int i = 0; i < 4; i++) {
			if (distanceToPort[i] < min) {
				min = distanceToPort[i];
				startPortNumber = i;
			}
		}
	}

	/*
	 * public void chooseEndPort(MouseEvent e) { //Point endPoint = e.getPoint();
	 * //relative to basicObj panel Point endPoint =
	 * MouseInfo.getPointerInfo().getLocation();
	 * SwingUtilities.convertPointFromScreen(endPoint, canvas); for(int j=0;j<4;j++)
	 * { distanceToPort[j] = Math.sqrt( Math.pow(portArray[j].getLocation().x-
	 * endPoint.x, 2) + Math.pow(portArray[j].getLocation().y-endPoint.y, 2)); }
	 * double min = 10000; for(int i=0;i<4;i++) { if(distanceToPort[i] < min) { min
	 * = distanceToPort[i]; endPortNumber = i; } } }
	 */
	public void addLine() {

		if (canvas.frame.currentBtn == canvas.frame.assButton)
			canvas.line = new associationLine(canvas.tempStartPoint, canvas.tempEndPoint, canvas.tempStartPort,
					canvas.tempEndPort);
		if (canvas.frame.currentBtn == canvas.frame.genButton)
			canvas.line = new generalizationLine(canvas.tempStartPoint, canvas.tempEndPoint, canvas.tempStartPort,
					canvas.tempEndPort);
		if (canvas.frame.currentBtn == canvas.frame.comButton)
			canvas.line = new compositionLine(canvas.tempStartPoint, canvas.tempEndPoint, canvas.tempStartPort,
					canvas.tempEndPort);
		canvas.lineArray.add(canvas.line);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.assButton || canvas.frame.currentBtn == canvas.frame.genButton
				|| canvas.frame.currentBtn == canvas.frame.comButton && canvas.lineEndBlock != null) {
			// canvas.lineEndBlock.chooseEndPort(e);
			basicObj lineEndBlock = canvas.lineEndBlock;
			Point endPoint = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(endPoint, canvas);

			for (int j = 0; j < 4; j++) {
				lineEndBlock.distanceToPort[j] = Math.sqrt(Math
						.pow(lineEndBlock.portArray[j].block.getLocation().x + lineEndBlock.portArray[j].getLocation().x
								- endPoint.x, 2)
						+ Math.pow(lineEndBlock.portArray[j].block.getLocation().y
								+ lineEndBlock.portArray[j].getLocation().y - endPoint.y, 2));
			}
			double min = 10000;
			for (int i = 0; i < 4; i++) {
				if (lineEndBlock.distanceToPort[i] < min) {
					min = lineEndBlock.distanceToPort[i];
					lineEndBlock.endPortNumber = i;
				}
			}

			canvas.tempEndPoint = lineEndBlock.portArray[lineEndBlock.endPortNumber].getLocation();
			canvas.tempEndPort = lineEndBlock.portArray[lineEndBlock.endPortNumber];
			canvas.setPosition();
			addLine();
			canvas.repaint();
			canvas.tempStartPoint = null;
			canvas.tempEndPoint = null;
			canvas.tempStartPort = null;
			canvas.tempEndPort = null;

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.assButton || canvas.frame.currentBtn == canvas.frame.genButton
				|| canvas.frame.currentBtn == canvas.frame.comButton) {

			canvas.lineEndBlock = this;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.sltButton && parentComp == null) {
			moveStartPoint = MouseInfo.getPointerInfo().getLocation();
			SwingUtilities.convertPointFromScreen(moveStartPoint, this);
		}
		if (canvas.frame.currentBtn == canvas.frame.sltButton && parentComp != null) {
			findParent().getStartPoint();
		}

		if (canvas.frame.currentBtn == canvas.frame.sltButton) {
			objStart = new Point(e.getPoint().x + this.getLocation().x, e.getPoint().y + this.getLocation().y);
			// this.setLocation(movePoint);
		}
		if (canvas.frame.currentBtn == canvas.frame.assButton || canvas.frame.currentBtn == canvas.frame.genButton
				|| canvas.frame.currentBtn == canvas.frame.comButton) {
			chooseStartPort(e);

			canvas.tempStartPoint = this.portArray[this.startPortNumber].getLocation();
			canvas.tempStartPort = this.portArray[this.startPortNumber];

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.sltButton) {

			canvas.selectedObj.clear();
			this.showOne(this);
			this.selected = true;
			canvas.selectedObj.add(this);
		}
		if (canvas.frame.currentBtn == canvas.frame.classes) {
			System.out.println(e.getX() + "," + e.getY());
			Point drawPoint = new Point(e.getPoint());
			drawPoint = SwingUtilities.convertPoint(this, drawPoint.x, drawPoint.y, canvas);
			basicObj basic = new classObj(canvas);
			canvas.add(basic, 0);
			// basic.setLocation(drawPoint.x, drawPoint.y);
			basic.setLocation(drawPoint);
			canvas.basicArray.add(basic);

		}
		if (canvas.frame.currentBtn == canvas.frame.useCase) {
			System.out.println(e.getX() + "," + e.getY());
			Point drawPoint = new Point(e.getPoint());
			drawPoint = SwingUtilities.convertPoint(this, drawPoint.x, drawPoint.y, canvas);
			basicObj basic = new caseObj(canvas);
			canvas.add(basic, 0);
			basic.setLocation(drawPoint);
			canvas.basicArray.add(basic);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.assButton || canvas.frame.currentBtn == canvas.frame.genButton
				|| canvas.frame.currentBtn == canvas.frame.comButton) {

			canvas.lineEndBlock = null;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.sltButton && parentComp == null) {
			canvas.moveToFront(this);
			objDrag = new Point(e.getPoint().x + this.getLocation().x, e.getPoint().y + this.getLocation().y);
			this.setLocation(objDrag);

			canvas.setChangedPosition();
			canvas.repaint();

		}
		if (canvas.frame.currentBtn == canvas.frame.sltButton && parentComp != null) {
			findParent().move();

		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public Composition findParent() {
		if (parentComp == null)
			return null;
		return parentComp.findParent();
	}

}
