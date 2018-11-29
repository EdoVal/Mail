package server;

import common.Email;
import java.io.*;
import static java.lang.System.in;
import java.net.*;
import java.util.Scanner;

public class serverThreadIn extends Thread {

    Model model;
    ServerSocket s;
    FileWriter f;
    BufferedWriter b;

    public serverThreadIn(ServerSocket ss, Model model) {
        this.s = ss;
        this.model = model;
        try {
            f = new FileWriter("mail.txt", true); // server/mail.txt
            b = new BufferedWriter(f);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void run() {
        while (true) {
            try (Socket incoming = s.accept()) {
                InputStream inStream = incoming.getInputStream();
                Scanner in = new Scanner(inStream);
                String st = in.nextLine();
                
                Email e = new Email(st);
                if(!model.contains(e)){
                    b.append(st + "\n");
                    b.flush();
                }
                model.setNewMail(e);
                model.setNewLine("mail ricevuta da " + e.getSender());
            } catch (IOException e) {
                System.out.println(e);
            }finally {
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
