package ooad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class Myframe extends JFrame implements ActionListener{

	Box b1 = Box.createVerticalBox();
	public ArrayList<buttonMode> buttonList;
	buttonMode sltButton;
	buttonMode assButton;
	buttonMode genButton;
	buttonMode comButton;
	buttonMode classes;
	buttonMode useCase;
	buttonMode currentBtn;
	Canvas canvas;
	
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenuItem groupItem; 
	JMenuItem ungroupItem; 
	JMenuItem editItem; 
	Myframe(){
		//frame setting
		this.setTitle("Uml Editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,700);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		//this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		//canvas
		canvas = new Canvas(this);
		
		//menuBar
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		groupItem = new JMenuItem("Group");
		ungroupItem = new JMenuItem("UnGroup");
		editItem = new JMenuItem("change object name");
		
		
		//button
		sltButton = new buttonMode(this,new ImageIcon("img/select.png"),b1);
		assButton = new buttonMode(this,new ImageIcon("img/associate.png"),b1);
		genButton = new buttonMode(this, new ImageIcon("img/general.png"),b1);
		comButton = new buttonMode(this, new ImageIcon("img/composite.png"),b1);
		classes = new buttonMode(this, new ImageIcon("img/class.png"),b1);
		useCase = new buttonMode(this, new ImageIcon("img/usecase.png"),b1);
		
		buttonList = new ArrayList<buttonMode>();
		buttonList.add(sltButton);
		buttonList.add(assButton);
		buttonList.add(genButton);
		buttonList.add(comButton);
		buttonList.add(classes);
		buttonList.add(useCase);
		

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		editMenu.add(groupItem);
		editMenu.add(ungroupItem);
		editMenu.add(editItem);
		
		groupItem.addActionListener(this);
		ungroupItem.addActionListener(this);
		editItem.addActionListener(this);
		this.setJMenuBar(menuBar);
			
		
		this.add(b1,BorderLayout.WEST);
		this.add(canvas,BorderLayout.CENTER);
		//this.add(b1);
		//this.add(canvas);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	
	public void stateChange(buttonMode selectButton) {
			
		if(currentBtn == selectButton) {
			selectButton.setSelected(true);
		}
		else {
			if(currentBtn != null)
			currentBtn.setSelected(false);
			currentBtn = selectButton;
			for(JButton i : buttonList) {
				if(i.isSelected()) {
					i.setBackground(new Color(0x2f4f4f));	
				}
				else {
					i.setBackground(new Color(0x696969));
					i.setSelected(false);
				}
			
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == groupItem) {
			canvas.group();
		}
		if(e.getSource() == ungroupItem) {
			
		}
		if(e.getSource() == editItem) {
			
		}
		
		
		
	}
	
}
