package ooad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane implements MouseListener, MouseMotionListener {

	public Myframe frame;
	// craeteObj point
	private Point drawPoint;
	// select window
	public Point startPoint;
	public Point dragPoint;
	public Point endPoint;
	public int windowWidth;
	public int windowHeight;
	// line tempPoints and ports
	public Point tempStartPoint;
	public Point tempEndPoint;
	public Port tempStartPort;
	public Port tempEndPort;

	public basicObj lineEndBlock;

	public ArrayList<basicObj> basicArray;
	public ArrayList<basicObj> selectedObj;
	public ArrayList<basicLine> lineArray;

	public ArrayList<Composition> groupArray;
	public ArrayList<Composition> selectedComposition;
	public Composition selectedGroup;

	public basicLine line;

	Canvas(Myframe frame) {

		this.setOpaque(true);
		this.setLayout(null);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.DARK_GRAY);
		this.frame = frame;
		basicArray = new ArrayList<basicObj>();
		selectedObj = new ArrayList<basicObj>();
		groupArray = new ArrayList<Composition>();
		selectedComposition = new ArrayList<Composition>();
		lineArray = new ArrayList<basicLine>();
		selectedGroup = null;
		lineEndBlock = null;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawWindow(g);
		drawLine(g);

	}

	public void setPosition() {

		tempStartPoint.x += tempStartPort.block.getLocation().x;
		tempStartPoint.y += tempStartPort.block.getLocation().y;
		tempEndPoint.x += tempEndPort.block.getLocation().x;
		tempEndPoint.y += tempEndPort.block.getLocation().y;

		/*
		 * line.lineStartPoint.x = lineStartBlock.getLocation().x +
		 * lineStartBlock.portArray[lineStartBlock.startPortNumber].getLocation().x;
		 * line.lineStartPoint.y = lineStartBlock.getLocation().y +
		 * lineStartBlock.portArray[lineStartBlock.startPortNumber].getLocation().y;
		 * line.lineEndPoint.x = lineEndBlock.getLocation().x +
		 * lineEndBlock.portArray[lineEndBlock.endPortNumber].getLocation().x;
		 * line.lineEndPoint.y = lineEndBlock.getLocation().y +
		 * lineEndBlock.portArray[lineEndBlock.endPortNumber].getLocation().y;
		 * 
		 * line.lineStartPort =
		 * lineStartBlock.portArray[lineStartBlock.startPortNumber]; line.lineEndPort =
		 * lineEndBlock.portArray[lineEndBlock.endPortNumber];
		 */
	}

	public void setChangedPosition() {
		for (basicLine i : lineArray) {
			i.lineStartPoint = SwingUtilities.convertPoint(i.lineStartPort.block, i.lineStartPort.getLocation().x,
					i.lineStartPort.getLocation().y, this);
			i.lineEndPoint = SwingUtilities.convertPoint(i.lineEndPort.block, i.lineEndPort.getLocation().x,
					i.lineEndPort.getLocation().y, this);

		}
	}

	public void drawWindow(Graphics g) {
		if (startPoint != null && dragPoint != null) {
			Graphics2D g2d = (Graphics2D) g;
			// g2d.drawRect(startPoint, dragPoint, windowWidth, windowHeight);
			Rectangle rect = new Rectangle(startPoint);
			rect.add(dragPoint);
			g2d.setColor(new Color(30, 144, 255, 30));
			g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}

	public void drawLine(Graphics g) {

		if (!lineArray.isEmpty()) {
			for (basicLine i : lineArray) {

				i.draw(g);
			}
		}

	}

	public void group() {
		if (selectedObj.isEmpty() && selectedComposition.isEmpty())
			return;
		Composition composite = new Composition(this, selectedObj, selectedComposition);
		add(composite);
		groupArray.add(composite);
		revalidate();
		repaint();

	}

	/*
	 * public void unGroup() { if (selectedGroup == null) return; for (basicObj i :
	 * selectedGroup.subBlock) i.parentComp = null; for (Composition i :
	 * selectedGroup.subComposite) i.parent = null; remove(selectedGroup);
	 * selectedGroup = null; revalidate(); repaint(); }
	 */
	public void unGroup() {
		if (selectedGroup == null)
			return;
		for (basicObj i : selectedGroup.subBlock)
			i.parentComp = null;
		for (Composition i : selectedGroup.subComposite)
			i.parent = null;
		remove(selectedGroup);
		selectedGroup = null;
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (frame.currentBtn == frame.classes) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new classObj(this);
			this.add(basic, 0);
			// basic.setLocation(drawPoint.x, drawPoint.y);
			basic.setLocation(drawPoint);
			basicArray.add(basic);

		}
		if (frame.currentBtn == frame.useCase) {
			System.out.println(e.getX() + "," + e.getY());
			drawPoint = new Point(e.getPoint());
			basicObj basic = new caseObj(this);
			this.add(basic, 0);
			basic.setLocation(drawPoint);
			basicArray.add(basic);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (frame.currentBtn == frame.sltButton) {
			selectedObj.clear();
			selectedComposition.clear();
			startPoint = e.getPoint();
			System.out.println(startPoint);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (frame.currentBtn == frame.sltButton) {
			endPoint = e.getPoint();
			windowWidth = Math.abs(endPoint.x - startPoint.x);
			windowHeight = Math.abs(endPoint.y - startPoint.y);
			int xMin,xMax,yMin,yMax;
			xMin = Math.min(startPoint.x, endPoint.x);
			xMax = xMin + windowWidth;
			yMin = Math.min(startPoint.y, endPoint.y);
			yMax = yMin +  windowHeight;
					
			for (basicObj i : basicArray) {
				Point location = i.getLocation();
				if (location.x > xMin && location.x < xMax && location.y > yMin
						&& location.y < yMax) {

					if (i.parentComp == null)
						selectedObj.add(i);
					else {
						Composition tmp = i.findParent();
						if (!selectedComposition.contains(tmp))
							selectedComposition.add(tmp);
						
						
					}

				}
			}

			for (basicObj j : selectedObj) {
				j.showPort();
			}
			if (selectedObj.isEmpty()) {
				for (basicObj j : basicArray) {
					j.hidePort();
				}
			}
			startPoint = null;
			endPoint = null;
			revalidate();
			repaint();

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (frame.currentBtn == frame.sltButton) {
			dragPoint = e.getPoint();
			repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
