import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*; 
import java.awt.Desktop;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
    static DataOutputStream outputStream;
	
	LocalDateTime time = LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
	String formattedTime = time.format(format);
	
	public GUI() throws UnknownHostException, IOException {
		System.out.println("User Logged in");
		socket = new Socket("localhost", 3000);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
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
			} 
			else if (combo.getSelectedItem().equals("Client")) {
				labelA.setText("Job ID");
				labelB.setText("Job Deadline");
				labelC.setText("Approximate Job Duration");
				button2.setText("Show Client Log");
				button3.setText("Calculate Completion Time");
			}
			emptyText();
		}

		if (e.getSource() == button) {
			try 
			{
				fileProcess();
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		} 
		
		if(e.getSource() == button2) {
			Desktop desktop = Desktop.getDesktop();
			try {
				if(combo.getSelectedItem().equals("Owner")){
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
			catch(IOException e1) {
				e1.printStackTrace();
			} 
		}

		//outputs the data into the output box
		if(combo.getSelectedItem().equals("Client") && e.getSource() == button3) {
			controller.calculateCompletionTime();
			String data = "";
			for (Job job: controller.getJobs()) {
				data += "Job " + job.id + ": " + job.completionTime + "\n"; 
			}
			output.setText(data);
		}
		
		if(combo.getSelectedItem().equals("Owner") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Owner ID: " + boxOne.getText() + "\n" + "Vehicle Info (Make, Model, Year): " + boxTwo.getText() + "\n" + "Residency Duration: " + boxThree.getText());
		
		}	
		else if(combo.getSelectedItem().equals("Client") && e.getSource() == button) 
		{
			output.setText("Information submitted." + formattedTime + "\n" + "Client ID: " + boxOne.getText() + "\n"+ "Job Deadline: " + boxTwo.getText() + "\n" + "Approximate Job Duration: " + boxThree.getText());

		}
		emptyText();
	}
	
	public void fileProcess() throws UnknownHostException, IOException{
			int id = Integer.parseInt(boxOne.getText());
			String info = boxTwo.getText();
			double duration = Double.parseDouble(boxThree.getText());
			String messageIn = "";
			try {
				if(combo.getSelectedItem().equals("Owner")) {
					Vehicle newVehicle = new Vehicle(id, info,duration);
					outputStream.writeUTF(newVehicle.toString());
					controller.recruitVehicle(newVehicle);
					messageIn = inputStream.readUTF();
					if(messageIn.equals("accept")) {
						System.out.println("Vehicle Accepted");
						writeToFile(newVehicle.toString(), ownerFile);
					}
				}
				else if(combo.getSelectedItem().equals("Client")) {
					Job newJob = new Job(id,info,duration);
					outputStream.writeUTF(newJob.toString());
					controller.submitJob(newJob);
					messageIn = inputStream.readUTF();
					if(messageIn.equals("accept")) {
						System.out.println("Job Accepted");
						writeToFile(newJob.toString(), clientFile);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		void emptyText(){
			boxOne.setText("");
			boxTwo.setText("");
			boxThree.setText("");
		}

		void writeToFile(String toFile, File file) throws IOException {
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
				fileWriter.newLine();
				fileWriter.write("Timestamp: " + formattedTime);
				fileWriter.newLine();
				fileWriter.write(toFile);
				fileWriter.newLine();
				fileWriter.close();
		}
}
