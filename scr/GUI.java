import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
public class GUI implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	private JLabel labelA;
	private JLabel labelB;
	private JLabel labelC;
	private JButton buttonA;
	
	
	public GUI(){
		frame= new JFrame();
		panel = new JPanel();
		labelA = new JLabel("Owner");
		labelB = new JLabel("UwU");
		labelC = new JLabel("Client");
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
		panel.setLayout(new GridLayout(1,3));
		panel.add(labelA);
		panel.add(labelB);
		panel.add(labelC);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cars");
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		new GUI();
	}
}
