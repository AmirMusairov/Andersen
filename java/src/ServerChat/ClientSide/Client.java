package ServerChat.ClientSide;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 2021;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Got connection!");

            InputStream inp = socket.getInputStream();
            OutputStream outp = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inp);
            DataOutputStream out = new DataOutputStream(outp);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Enter your message to server:");

            while (true) {
                line = keyboard.readLine();
                System.out.println("Sending this line to the server...");
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                System.out.println("The Server sent your message back : " + line);
                if (line.equalsIgnoreCase("quit")) {
                    socket.close();
                    System.out.println("Server is closed. See you next time!");
                    break;
                }
                System.out.println("You can sent more messages:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}