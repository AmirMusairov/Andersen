package KafkaChat;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KafkaUser client = new KafkaUser(args[0], args[1]);
        try {
            boolean stop = false;
            do {
                System.out.println("prompt>");
                Scanner sc = new Scanner(System.in);
                String str = sc.nextLine();
                String[] splited = str.split("\\s+");
                if (splited[0].equals("/AMIR")) {
                    if (splited.length != 2) {
                        System.out.println("Usage: /AMIR <nickname>");
                    } else {
                        client.login(splited[1]);
                        System.out.println("Nickname changed to '" + splited[1] + "'");
                    }

                } else if (splited[0].equals("/JOIN")) {
                    if (splited.length != 2) {
                        System.out.println("Usage: /JOIN <nickname>");
                    } else {
                        client.join(splited[1]);
                    }

                } else if (splited[0].equals("/LEAVE")) {
                    if (splited.length != 2) {
                        System.out.println("Usage: /LEAVE <channel>");
                    } else {
                        client.leave(splited[1]);
                    }
                } else if (splited[0].equals("/EXIT")) {
                    stop = true;
                    client.exit();
                } else {
                    StringBuffer message = new StringBuffer();
                    if (splited[0].startsWith("@")) {
                        if (splited.length < 2) {
                            System.out.println("Usage: @<channel> <text>");
                        } else {
                            String channelName = splited[0].substring(1);
                            for (int i = 1; i < splited.length; i++) {
                                if (i > 1) message.append(" ");
                                message.append(splited[i]);
                            }
                            client.send(channelName, message.toString());
                        }
                    } else {
                        for (int i = 0; i < splited.length; i++) {
                            if (i > 0) message.append(" ");
                            message.append(splited[i]);

                        }
                        client.sendAll(message.toString());
                    }
                }
                if (!stop) {
                    List<String> messages = client.recieveMessages();
                    for (String message : messages) {
                        System.out.println(message);
                    }
                }
            } while (!stop);
            System.out.println("bye");
        } finally {
            client.exit();
        }
    }
}
