import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.*;
public class GUI extends JFrame implements ActionListener {

	// Instance variables
	private JPanel dropdownPanel, textInputPanel, buttonInputPanel, textOutputPanel;
	private JTextField boxOne, boxTwo, boxThree;
	private JTextArea output;
	private JLabel labelA, labelB, labelC;
	private JComboBox combo;
	private JButton button;
	private File ownerFile = new File("Owner.txt");
	private File clientFile = new File("Client.txt");	
	
	public GUI() {

		//Setting the layout, size, and title of the GUI
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("Cars");
		this.setSize(600,800);

		//Instantaited panels in the GUI
		dropdownPanel = new JPanel();

		textInputPanel = new JPanel();
		textInputPanel.setLayout(new GridLayout(3,2));

		buttonInputPanel = new JPanel();

		textOutputPanel = new JPanel();
		textOutputPanel.setLayout(new BorderLayout());
		
		//Combo box instantiation, goes into the dropdown Panel
		String[] choices = {" ","Owner", "Client"};
		combo = new JComboBox(choices);
		combo.addActionListener(this);

		//All the text boxes and their labels, goes into the text input panel
		boxOne = new JTextField();
		boxOne.setPreferredSize(new Dimension(250,40));
		boxTwo = new JTextField();
		boxTwo.setPreferredSize(new Dimension(250,40));
		boxThree = new JTextField();
		boxThree.setPreferredSize(new Dimension(250,40));

		labelA = new JLabel("Nothing Selected", SwingConstants.CENTER);
		labelB = new JLabel("Nothing Selected", SwingConstants.CENTER);
		labelC = new JLabel("Nothing Selected", SwingConstants.CENTER);

		//Text area at the lowest panel of the JFrame
		//Not editable, plan to add feature that outputs what will be input by the user
		output = new JTextArea();
		output.setEditable(false);
		output.setMargin( new Insets(10,10,10,10) );

		//Button to submit what was input, goes into the button input panel
		button = new JButton("Submit");
		button.addActionListener(this);

		//Adds all elements to their respective panels
		dropdownPanel.add(combo);
		textInputPanel.add(labelA);
		textInputPanel.add(boxOne);
		textInputPanel.add(labelB);
		textInputPanel.add(boxTwo);
		textInputPanel.add(labelC);
		textInputPanel.add(boxThree);
		buttonInputPanel.add(button);
		textOutputPanel.add(output);
		
		//Adds each panel to the main JFrame
		this.add(dropdownPanel);
		this.add(textInputPanel);
		this.add(buttonInputPanel);
		this.add(textOutputPanel);

		this.pack();
		this.setVisible(true);		
	}

	//Action listener method
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo)
		{
			//Sees which choice in the dropdown gets selected 
			//And changes the labels accordingly
			if (combo.getSelectedItem().equals("Owner"))
			{
				labelA.setText("Owner ID");
				labelB.setText("Vehicle Info");
				labelC.setText("Residency Time");
			}
			else if (combo.getSelectedItem().equals("Client"))
			{
				labelA.setText("Client ID");
				labelB.setText("Approximate Time");
				labelC.setText("Job Deadline");
			}
		}
		
		//when the button is pressed, the bottom text area should output what was input. 
		if(e.getSource() == button)
		{
			try 
			{
				fileProcess();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			output.setText("Information submitted!\n" + boxOne.getText() + "\n" + boxTwo.getText() + "\n" + boxThree.getText() + "\n" );
		}
	}
	
	public void fileProcess() throws IOException {
			if(combo.getSelectedItem().equals("Owner")) {
				BufferedWriter ownerWriter = new BufferedWriter(new FileWriter(ownerFile,true));
				ownerWriter.write(boxOne.getText());
				ownerWriter.newLine();
				ownerWriter.write(boxTwo.getText());
				ownerWriter.newLine();
				ownerWriter.write(boxThree.getText());
				ownerWriter.newLine();
				ownerWriter.newLine();
				ownerWriter.close();
			}
			else if (combo.getSelectedItem().equals("Client")) {
				BufferedWriter clientWriter = new BufferedWriter(new FileWriter(clientFile,true));
				clientWriter.write(boxOne.getText());
				clientWriter.newLine();
				clientWriter.write(boxTwo.getText());
				clientWriter.newLine();
				clientWriter.write(boxThree.getText());
				clientWriter.newLine();
				clientWriter.newLine();
				clientWriter.close();
			}
		}
}
