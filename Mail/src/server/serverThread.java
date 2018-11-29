package server;

import common.Email;
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class serverThread extends Thread {

    Model model;
    ServerSocket s;

    public serverThread(ServerSocket ss, Model model) {
        this.s = ss;
        this.model = model;
    }

    @Override
    public void run() {
        try {
            try (Socket incoming = s.accept()) {

                //RICEVE NOME UTENTE
                InputStream inStream = incoming.getInputStream();
                Scanner in = new Scanner(inStream);
                String userName = in.nextLine();
                model.setNewLine(userName + " ha stabilito la connesione");

                //INVIA LISTA MAIL
                int size = 0;
                while (true) {
                    ArrayList<Email> list = model.getMailLisOf(userName);
                    if (list.size() != size) {
                        ObjectOutputStream outStream = new ObjectOutputStream(incoming.getOutputStream());
                        outStream.writeObject(list); //dovrebbe scrivere list ma non è serializzabile
                        model.setNewLine("lista di mail inviata a " + userName);
                    }
                    size = list.size();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
