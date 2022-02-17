import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class GUI extends JFrame implements ActionListener{
	private JPanel topPanel;
	private JPanel bottomPanel;

	private JTextField textField;
	private JButton button;
	private JComboBox<String> comboBox;
	
	
	public GUI(){

		
		//panels + layout
		topPanel = new JPanel();
		topPanel.setBounds(0,0,750,150);
		topPanel.setBackground(Color.red);

		bottomPanel = new JPanel();
		bottomPanel.setBounds(0,150,750, 600);
		bottomPanel.setBackground(Color.GRAY);

		//Dropdown menu
		String[] options = {"Owner", "Client"};
		comboBox = new JComboBox<String>(options);
		comboBox.addActionListener(this);

		//text fields
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250,40));

		JButton button = new JButton("Submit");
		button.addActionListener(this);


		
		topPanel.add(comboBox, BorderLayout.CENTER);
		bottomPanel.add(textField);
		bottomPanel.add(button);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Vehicual Cloud Program");
		this.setLayout(null);
		this.add(topPanel);
		this.add(bottomPanel);
		this.pack();
		this.setSize(750,750);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox) {
			System.out.println(comboBox.getSelectedItem());
		}

		if (e.getSource() == button){
			System.out.println("Hello World");
		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		new GUI();
	}
}
