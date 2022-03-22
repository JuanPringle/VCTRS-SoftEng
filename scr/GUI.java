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
	private JButton button,button2, button3;
	private File ownerFile = new File("Owner.txt");
	private File clientFile = new File("Client.txt");
	private Controller controller = new Controller();
	
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
		buttonInputPanel.setLayout(new FlowLayout());

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

		button3 = new JButton("Stuff");
		button3.addActionListener(this);

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
		buttonInputPanel.add(button3);
		textOutputPanel.add(output);
		
		//Adds each panel to the main JFrame
		this.add(dropdownPanel);
		this.add(textInputPanel);
		this.add(buttonInputPanel);
		this.add(textOutputPanel);

		this.pack();
		this.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo) {

			if (combo.getSelectedItem().equals("Owner")) {
				labelA.setText("Vehicle ID");
				labelB.setText("Vehicle Info (Make, Model, Year)");
				labelC.setText("Residency Duration");
				button2.setText("Show Owner Log");
				button3.setText("Do Stuff");
			} else if (combo.getSelectedItem().equals("Client")) {
				labelA.setText("Job ID");
				labelB.setText("Job Deadline");
				labelC.setText("Approximate Job Duration");
				button2.setText("Show Client Log");
				button3.setText("Calculate Completion Time");
			}
		}

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
		
		if(e.getSource() == button2) {
			Desktop desktop = Desktop.getDesktop();
			try {
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
			catch (IOException e1) {
				e1.printStackTrace();
			} 
		}

		if (combo.getSelectedItem().equals("Client") && e.getSource() == button3) {
			controller.calculateCompletionTime();
			String data = "";
			int counter = 1;
			for (Job job: controller.getJobs()) {
				data += "Job " + counter + ": " + job.completionTime + "\n"; 
				counter++;
			}
			output.setText(data);
		}
		
		if (combo.getSelectedItem().equals("Owner") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Owner ID: " + boxOne.getText() + "\n" + "Vehicle Info (Make, Model, Year): " + boxTwo.getText() + "\n" + "Residency Duration: " + boxThree.getText());
			emptyText();		
		}	
		else if (combo.getSelectedItem().equals("Client") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Client ID: " + boxOne.getText() + "\n" + "Approximate Job Duration: " + boxTwo.getText() + "\n" + "Job Deadline: " + boxThree.getText());
			emptyText();
		}
	}
	
	public void fileProcess() throws IOException {
			int id = Integer.parseInt(boxOne.getText());
			String info = boxTwo.getText();
			double duration = Double.parseDouble(boxThree.getText());
			BufferedWriter fileWriter;
			
			if(combo.getSelectedItem().equals("Owner")) {
				fileWriter = new BufferedWriter(new FileWriter(ownerFile,true));
				Vehicle newVehicle = new Vehicle(id, info,duration);
				controller.recruitVehicle(newVehicle);
				fileWriter.write("Timestamp: " + formattedTime);
				fileWriter.newLine();
				fileWriter.write(newVehicle.toString());
				fileWriter.newLine();
				fileWriter.close();
			}
			else if (combo.getSelectedItem().equals("Client")) {
				fileWriter = new BufferedWriter(new FileWriter(clientFile,true));
				Job newJob = new Job(id, info,duration);
				controller.submitJob(newJob);
				fileWriter.write("\nTimestamp: " + formattedTime + "\n");
				fileWriter.write(newJob.toString());
				fileWriter.newLine();
				fileWriter.close();
			}
			
		}

		void emptyText(){
			boxOne.setText("");
			boxTwo.setText("");
			boxThree.setText("");
		}
}
