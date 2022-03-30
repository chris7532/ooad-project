package ooad;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Composition extends JPanel implements MouseListener, MouseMotionListener {

	public ArrayList<basicObj> subBlock;
	public ArrayList<Composition> subComposite;
	public int xMin = 10000, xMax = 0, yMin = 10000, yMax = 0, xSize, ySize;
	public int offset = 20;
	public Point moveStartPoint;
	public Point location;
	public Point dragPoint;
	public Composition parent;
	public Canvas canvas;
	public Point P;
	public double parentDistanceX, parentDistanceY;
	public boolean travesalTag = true;

	Composition(Canvas canvas, ArrayList<basicObj> subBlock, ArrayList<Composition> subComposite) {
		this.canvas = canvas;
		this.subBlock = new ArrayList<basicObj>(subBlock);
		this.subComposite = new ArrayList<Composition>(subComposite);
		findBounds();
		xSize = xMax - xMin;
		ySize = yMax - yMin;
		this.setSize(xSize, ySize);
		this.setLocation(xMin - 10, yMin - 10);
		this.setBorder(BorderFactory.createLineBorder(new Color(255, 228, 181, 200), 3));
		this.setBackground(new Color(255, 228, 196, 30));
		parent = null;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		getParentDistance();
	}

	public void findBounds() {

		for (basicObj obj : subBlock) {
			obj.parentComp = this;
			if (obj.getLocation().x < xMin) {
				xMin = obj.getLocation().x;
			}
			if (obj.getLocation().x > xMax) {
				xMax = obj.getLocation().x;
			}
			if (obj.getLocation().y < yMin) {
				yMin = obj.getLocation().y;
			}
			if (obj.getLocation().y > yMax) {
				yMax = obj.getLocation().y;
			}
		}
		this.xMin -= offset;
		this.xMax += (120 + offset + 10);
		this.yMin -= offset;
		this.yMax += (140 + offset + 10);
		if (!subComposite.isEmpty()) {

			for (Composition i : subComposite) {
				i.parent = this;
				if (i.getLocation().x < xMin) {
					xMin = i.getLocation().x;
				}
				if (i.getLocation().x + i.xSize > xMax) {
					xMax = i.getLocation().x + i.xSize;
				}
				if (i.getLocation().y < yMin) {
					yMin = i.getLocation().y;
				}
				if (i.getLocation().y + i.ySize > yMax) {
					yMax = i.getLocation().y + i.ySize;
				}
			}

			this.xMin -= 20;
			this.xMax += 20 * 2;
			this.yMin -= 20;
			this.yMax += 20 * 2;
		}

	}

	public void getParentDistance() {
		for (basicObj i : subBlock) {
			i.parentDistanceX = i.getX() - this.getX();
			i.parentDistanceY = i.getY() - this.getY();
		}
		for (Composition i : subComposite) {
			i.parentDistanceX = i.getX() - this.getX();
			i.parentDistanceY = i.getY() - this.getY();
		}
	}

	public void getStartPoint() {

		moveStartPoint = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(moveStartPoint, this);
	}

	public Composition findParent() {
		Composition tmp = this;
		while (tmp.parent != null && travesalTag) {
			tmp = tmp.parent;
		}
		return tmp;
	}

	public void moveOutside() {
		findParent().dragPoint = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(findParent().dragPoint, canvas);
		findParent().dragPoint.x -= moveStartPoint.x;
		findParent().dragPoint.y -= moveStartPoint.y;
		this.setLocation(dragPoint);
		this.moveInside();
		canvas.repaint();
	}

	public void moveInside() {
		canvas.moveToFront(this);
		for (basicObj i : subBlock) {
			canvas.moveToFront(i);
			i.setLocation((int) (i.parentComp.getX() + i.parentDistanceX),
					(int) (i.parentComp.getY() + i.parentDistanceY));
			i.canvas.setChangedPosition();
		}
		if (this.subComposite.isEmpty())
			return;
		for (Composition i : subComposite) {
			canvas.moveToFront(i);
			i.setLocation((int) (i.parent.getX() + i.parentDistanceX), (int) (i.parent.getY() + i.parentDistanceY));
			i.moveInside();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (canvas.frame.currentBtn == canvas.frame.sltButton && parent == null) {
			getStartPoint();
		}
		if (canvas.frame.currentBtn == canvas.frame.sltButton && parent != null) {
			findParent().getStartPoint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.sltButton)
			canvas.selectedGroup = findParent();
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (canvas.frame.currentBtn == canvas.frame.sltButton && this.parent == null) {
			moveOutside();
		} 
		if (canvas.frame.currentBtn == canvas.frame.sltButton && this.parent != null) {
			findParent().moveOutside();
		} 
		
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
