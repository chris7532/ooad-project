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
	
	
	Myframe(){
		//frame setting
		this.setTitle("Uml Editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,700);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		//canvas
		Canvas canvas = new Canvas(this);
		
		//menuBar
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem groupItem = new JMenuItem("Group");
		
		//button
		sltButton = new buttonMode(this,new ImageIcon("select.png"),b1);
		assButton = new buttonMode(this,new ImageIcon("associate.png"),b1);
		genButton = new buttonMode(this, new ImageIcon("general.png"),b1);
		comButton = new buttonMode(this, new ImageIcon("composite.png"),b1);
		classes = new buttonMode(this, new ImageIcon("class.png"),b1);
		useCase = new buttonMode(this, new ImageIcon("usecase.png"),b1);
		
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
		this.setJMenuBar(menuBar);
			
		
		this.add(b1,BorderLayout.WEST);
		this.add(canvas,BorderLayout.CENTER);
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
		
		
		
		
	}
	
}
