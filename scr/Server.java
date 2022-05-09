import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Server extends Thread {
    static ServerSocket serverSocket;
    static ServerSocket serverSocket2;
    static Socket socket;
    static Socket socket2;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    static DataInputStream inputStream2;
    static DataOutputStream outputStream2;
	
    public static void main(String[] args) throws UnknownHostException, IOException {
    	String messageIn = "";
        String messageOut = "";
        String response = "";
    	boolean isRunning = false;
    	serverSocket = new ServerSocket(3000);
    	serverSocket2 = new ServerSocket(3001);
        
        try {
        	isRunning = true;
        	ServerGUI serverGUI = new ServerGUI();
            System.out.println("This is the server");
            System.out.println("Waiting for request");
            
            socket = serverSocket.accept();
            System.out.println("User Connected");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            socket2 = serverSocket2.accept();
            
            while(isRunning)
            {
            	serverGUI.messageIn = inputStream.readUTF();
            	serverGUI.incomingRequest.setText(serverGUI.messageIn);
                System.out.println("Message received");
                
                inputStream2 = new DataInputStream(socket2.getInputStream());
        		outputStream2 = new DataOutputStream(socket2.getOutputStream());
        		serverGUI.messageOut = inputStream2.readUTF();
        		if(serverGUI.messageOut.equals("Accepted")) {
                	System.out.println("Submission accepted");
                    outputStream.writeUTF(serverGUI.messageOut);
                }
                else if(serverGUI.messageOut.equals("Rejected")) {
                	System.out.println("Submission rejected");
                	outputStream.writeUTF(serverGUI.messageOut);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}