package ooad;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Myframe extends JFrame{

	Box b1 = Box.createVerticalBox();
	Myframe(){
		this.setTitle("Uml Editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		
		JPanel panel = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		JMenuItem groupItem = new JMenuItem("Group");
		
		
		JButton sltButton = new JButton("select");
		JButton assButton = new JButton("association");
		JButton genButton = new JButton("generalization");
		JButton comButton = new JButton("composition");
		JButton classes = new JButton("class");
		JButton useCase = new JButton("use case");
		
		
		setButton(sltButton);
		setButton(assButton);
		setButton(genButton);
		setButton(comButton);
		setButton(classes);
		setButton(useCase);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		editMenu.add(groupItem);
		this.setJMenuBar(menuBar);
		
		
		this.add(b1);
		this.setVisible(true);
		
	}
	
	private void setButton(JButton button) {
		button.setFocusable(false);
		b1.add(button);
		b1.add(Box.createVerticalStrut(50));
		
	}
	
}
