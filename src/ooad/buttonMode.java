package ooad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class buttonMode extends JButton{
	Myframe frame;

    buttonMode(Myframe frame, ImageIcon icon, Box b1) {
        super(icon);
        this.frame = frame;
        this.setOpaque(true);
        this.setFocusable(false);
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(new Color(0x696969));
		b1.add(this);
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stateChange();
            }
        });
    }
    public void stateChange() {
    	this.setSelected(true);
    	frame.stateChange(this);
		
    }
    
		
	
	
	
}
