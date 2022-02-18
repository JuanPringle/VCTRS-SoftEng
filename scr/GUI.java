import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class GUI extends JFrame implements ActionListener{
	private JPanel topPanel;
	private JPanel bottomPanel;

	private JTextField id;
	private JTextField info;
	private JTextField time;
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
		id = new JTextField();
		id.setPreferredSize(new Dimension(250,40));

		info = new JTextField();
		info.setPreferredSize(new Dimension(250,40));

		time = new JTextField();
		time.setPreferredSize(new Dimension(250,40));

		button = new JButton("Submit");
		button.addActionListener(this);


		
		topPanel.add(comboBox, BorderLayout.CENTER);
		bottomPanel.add(id);
		bottomPanel.add(info);
		bottomPanel.add(time);
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
			System.out.println(id.getText());
		}
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		new GUI();
	}
}
