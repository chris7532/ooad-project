package ooad;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Port extends JPanel {

	public basicObj block;
	public Canvas canvas;

	Port() {
	}

	Port(basicObj block) {
		this.setLayout(null);
		this.setBackground(Color.white);
		this.block = block;

	}

}
