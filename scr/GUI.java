import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener{

	// instance variables
	
	private JPanel dropdownPanel, textInputPanel, buttonInputPanel, textOutputPanel;
	private JTextField boxOne, boxTwo, boxThree;
	private JTextArea output;
	private JLabel labelA, labelB, labelC;
	private JComboBox combo;
	private JButton button;
	

	
	
	public GUI(){

		//setting the layout, size, and title of the GUI
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("Cars");
		this.setSize(600,800);

		//instantaited panels in the GUI
		dropdownPanel = new JPanel();

		textInputPanel = new JPanel();
		textInputPanel.setLayout(new GridLayout(3,2));

		buttonInputPanel = new JPanel();


		textOutputPanel = new JPanel();
		textOutputPanel.setLayout(new BorderLayout());
		

		//combo box instantiation, goes into the dropdown Panel
		String[] choices = {" ","Owner", "Client"};
		combo = new JComboBox(choices);
		combo.addActionListener(this);

		//all the text boxes and their labels, goes into the text input panel
		boxOne = new JTextField();
		boxOne.setPreferredSize(new Dimension(250,40));
		boxTwo = new JTextField();
		boxTwo.setPreferredSize(new Dimension(250,40));
		boxThree = new JTextField();
		boxThree.setPreferredSize(new Dimension(250,40));

		labelA = new JLabel("Nothing Selected", SwingConstants.CENTER);
		labelB = new JLabel("Nothing Selected", SwingConstants.CENTER);
		labelC = new JLabel("Nothing Selected", SwingConstants.CENTER);

		//text area at the lowest panel of the JFrame
		//not editable, plan to add feature that outputs what will be input by the user
		output = new JTextArea();
		output.setEditable(false);
		output.setMargin( new Insets(10,10,10,10) );


		//button to submit what was input, goes into the button input panel
		button = new JButton("Submit");
		button.addActionListener(this);

		//adds all elements to their respective panels
		dropdownPanel.add(combo);
		textInputPanel.add(labelA);
		textInputPanel.add(boxOne);
		textInputPanel.add(labelB);
		textInputPanel.add(boxTwo);
		textInputPanel.add(labelC);
		textInputPanel.add(boxThree);
		buttonInputPanel.add(button);
		textOutputPanel.add(output);
		
		//adds each panel to the main JFrame
		this.add(dropdownPanel);
		this.add(textInputPanel);
		this.add(buttonInputPanel);
		this.add(textOutputPanel);


		this.pack();
		this.setVisible(true);
		
	}

	// action listener method
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo) {
			// sees which choice in the dropdown gets selected
			// and changes the labels accordingly
			if (combo.getSelectedItem().equals("Owner")) {
				labelA.setText("Owner ID");
				labelB.setText("Vehicle Info (Make, Model, Year)");
				labelC.setText("Residency Time");
			} else if (combo.getSelectedItem().equals("Client")) {
				labelA.setText("Client ID");
				labelB.setText("Approximate Time");
				labelC.setText("Job Deadline");
			}
		}

		// this area needs more work
		// when the button is pressed, the bottom text area should output what was
		// input.
		// can delete this feature if not necessary
		// if (e.getSource() == button) {
		//	output.setText("Information submitted!\n");
		//}
		if (combo.getSelectedItem().equals("Owner") && e.getSource() == button) {
			output.setText("Information submitted." + "\n" + "Owner ID\n" + boxOne.getText() + "\n" + "Vehicle Info (Make, Model, Year)\n "
					+ boxTwo.getText() + "\n" + "Residency Time\n" + boxThree.getText());
		} else if (combo.getSelectedItem().equals("Client")) {
			output.setText("Information submitted." + "\n" + "Client ID\n" + boxOne.getText() + "\n" + "Approximate Time\n" + boxTwo.getText() + "\n"
					+ "Job Deadline\n" + boxThree.getText());
		}
	}

}
