import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*; 
import java.awt.Desktop;

public class GUI extends JFrame implements ActionListener {

	// Instance variables
	private JPanel dropdownPanel, textInputPanel, buttonInputPanel, textOutputPanel;
	private JTextField boxOne, boxTwo, boxThree;
	private JTextArea output;
	private JLabel labelA, labelB, labelC;
	private JComboBox combo;
	private JButton button;
	private JButton button2;
	private File ownerFile = new File("Owner.txt");
	private File clientFile = new File("Client.txt");
	
	LocalDateTime time = LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	String formattedTime = time.format(format);
	
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
		buttonInputPanel = new JPanel();

		textOutputPanel = new JPanel();
		textOutputPanel.setLayout(new BorderLayout());

		
		//Combo box instantiation, goes into the dropdown Panel
		String[] choices = {"Select ","Owner", "Client"};
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
		
		button2= new JButton("Show Logs");
		button2.addActionListener(this);

		//Adds all elements to their respective panels
		dropdownPanel.add(combo);
		textInputPanel.add(labelA);
		textInputPanel.add(boxOne);
		textInputPanel.add(labelB);
		textInputPanel.add(boxTwo);
		textInputPanel.add(labelC);
		textInputPanel.add(boxThree);
		buttonInputPanel.add(button);
		buttonInputPanel.add(button2);
		textOutputPanel.add(output);
		
		//Adds each panel to the main JFrame
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
				labelC.setText("Residency Duration");
				button2.setText("Show Owner Log");
			} else if (combo.getSelectedItem().equals("Client")) {
				labelA.setText("Client ID");
				labelB.setText("Approximate Job Duration");
				labelC.setText("Job Deadline");
				button2.setText("Show Client Log");
			}

		}
		
		//opens the
		if(e.getSource() == button2) {

			Desktop desktop = Desktop.getDesktop();
			//File ownerFile1 = new File("Owner.txt"); 

			try 
			{
			if (combo.getSelectedItem().equals("Owner")){
				if(ownerFile.exists()){
					desktop.open(ownerFile);   
				}
			} else if (combo.getSelectedItem().equals("Client")){
				if(clientFile.exists()){
					desktop.open(clientFile);
				}
			} else {
				desktop.open(ownerFile);   
				desktop.open(clientFile);
			}
				
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			} 
		
			
		}
		
		//when the button is pressed, the bottom text area should output what was input. 
		if (e.getSource() == button) {
			try 
			{
				fileProcess();
				
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		} 
		if (combo.getSelectedItem().equals("Owner") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Owner ID: " + boxOne.getText() + "\n" + "Vehicle Info (Make, Model, Year): " + boxTwo.getText() + "\n" + "Residency Duration: " + boxThree.getText());
			boxOne.setText("");
			boxTwo.setText("");
			boxThree.setText("");	
		}	
		else if (combo.getSelectedItem().equals("Client") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Client ID: " + boxOne.getText() + "\n" + "Approximate Job Duration: " + boxTwo.getText() + "\n" + "Job Deadline: " + boxThree.getText());
			boxOne.setText("");
			boxTwo.setText("");
			boxThree.setText("");
		}
	}
	
	public void fileProcess() throws IOException {
			if(combo.getSelectedItem().equals("Owner")) {
				BufferedWriter ownerWriter = new BufferedWriter(new FileWriter(ownerFile,true));
				ownerWriter.write("Timestamp: " + formattedTime);
				Owner newOwner = new Owner(Integer.parseInt(boxOne.getText()), boxTwo.getText(), boxThree.getText());
				ownerWriter.write(newOwner.toString());
				ownerWriter.newLine();
				ownerWriter.close();
			}
			else if (combo.getSelectedItem().equals("Client")) {
				BufferedWriter clientWriter = new BufferedWriter(new FileWriter(clientFile,true));
				clientWriter.newLine();
				clientWriter.write("Timestamp: " + formattedTime);
				Client newClient = new Client(Integer.parseInt(boxOne.getText()), boxTwo.getText(), boxThree.getText());
				clientWriter.write(newClient.toString());
				clientWriter.newLine();
				clientWriter.close();
			}
		}
}
