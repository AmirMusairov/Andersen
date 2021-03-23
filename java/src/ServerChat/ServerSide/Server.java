package ServerChat.ServerSide;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] ar) {
        int port = 2021;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");

            Socket socket = ss.accept();

            InputStream inp = socket.getInputStream();
            OutputStream outp = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inp);
            DataOutputStream out = new DataOutputStream(outp);

            String line = null;
            while (true) {
                line = in.readUTF();
                System.out.println("Client send a message : " + line);
                System.out.println("Server sending it back...");
                out.writeUTF(line);
                out.flush();
                if (line.equalsIgnoreCase("quit")) {
                    socket.close();
                    System.out.println("Server is closed. See you next time!");
                    break;
                }
                System.out.println("Waiting for the next line...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}