package server;

import common.Email;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

public class Model extends Observable {

    private ArrayList<String> log;
    //mappa tra utente e sua lista di mail ricevute
    private HashMap<String, ArrayList<Email>> mailList;

    public Model() {
        log = new ArrayList<>();
        mailList = new HashMap<>();
    }

    /* QUANDO I DESTINATARI SARANNO UNA LISTA:
    
    public void setNewSingleMail(Email e, String r){ 
        ArrayList<Email> temp = new ArrayList();
        if(mailList.containsKey(r))
            temp = mailList.get(r);
        temp.add(e);
        mailList.put(r, temp);
    }
    
    public void setNewMail(Email e){ 
        ArrayList<String> receiver = e.getReceiver();
        for(String r : receiver)
            setNewSingleMail(e, r);
    }
    
     */
    public void setNewMail(Email e) throws IOException {
        ArrayList<Email> temp = new ArrayList();
        if (mailList.containsKey(e.getReceiver())) {
            temp = mailList.get(e.getReceiver());
            if (temp.contains(e)) {
                System.out.println("prima della rimozione " + temp.size());
                temp.remove(e);
                removeMail(e);
                System.out.println("FATTO");
            } else {
                temp.add(e);
            }
        } else {
            temp.add(e);
        }
        System.out.println("dopo l'aggiunta " + temp.size());
        mailList.put(e.getReceiver(), temp);
        setChanged();
        notifyObservers(temp);
    }

    public void setNewLine(String l) {
        log.add(l);
        setChanged();
        notifyObservers(l);
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setNewMail(String m) {
        //aggiungi mail a hashamp
        notifyObservers(); //scrive la nuova mail sul file
    }

    public void removeMail(Email em) throws IOException {
        ArrayList<String> tmp = new ArrayList<>();
        BufferedWriter b = null;
        Scanner file = null;
        try {
            file = new Scanner(new File("mail.txt"));
            b = new BufferedWriter(new FileWriter("mail.txt"));
            while (file.hasNextLine()) {
                String str = file.nextLine();
                if (!em.toString().equals(str))
                    tmp.add(str);
            }
            for (String s : tmp) {
                b.append(s).append("\n");
                b.flush();
            }
        } finally {
            file.close();
            b.close();
        }
    }
    public boolean contains(Email e){ return mailList.get(e.getReceiver()).contains(e); }
    public ArrayList<Email> getMailLisOf(String s) {
        return mailList.get(s);
    }
}
