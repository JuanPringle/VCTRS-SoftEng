import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;

    public static void main(String[] args) {
        String messageIn = "";
        String messageOut = "";
        String response = "";
        Scanner input;
        boolean isRunning = false;

        try {
            isRunning = true;
            System.out.println("This is the server");
            System.out.println("Waiting for request");
            serverSocket = new ServerSocket(3000);
            socket = serverSocket.accept();

            while (!messageIn.equals("exit")) {
                System.out.println("User Connected");
                System.out.println("Awaiting message");

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                
                messageIn = inputStream.readUTF();
                System.out.println("Accept or reject?");
                input = new Scanner(System.in);
                response = input.nextLine();
                if (response.toLowerCase().equals("accept")) {
                    System.out.println("Submission accepted");
                    messageOut = "accepted";
                }
                else if(response.toLowerCase().equals("reject"))
                {
                    System.out.println("Submission rejected");
                    messageOut = "rejected";
                }
                 else {
                    System.out.println("That is not a valid response");
                }

                outputStream.writeUTF(messageOut);
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

