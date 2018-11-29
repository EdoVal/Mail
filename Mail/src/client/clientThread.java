package client;

import common.Email;
import java.io.*;
import java.util.*;
import java.net.*;

public class clientThread extends Thread {

    Model model;

    public clientThread(Model m) {
        this.model = m;
    }

    @Override
    public void run() {
        try {
            String nomeHost = InetAddress.getLocalHost().getHostName();
            Socket s = new Socket(nomeHost, 8189);

            //INVIA NOME UTENTE
            OutputStream outStreamName = s.getOutputStream();
            PrintWriter outName = new PrintWriter(outStreamName, true);
            outName.println(model.getUserName());

            //RICEVE MAIL LIST
            while (true) {
                ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
                ArrayList<Email> list = ((ArrayList<Email>) inStream.readObject());
                model.setMailList(list);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
