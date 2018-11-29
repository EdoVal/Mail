/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import common.Email;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class clientThreadOut extends Thread {

    Email mail;
    Model model;

    public clientThreadOut(Email e, Model m) {
        this.mail = e;
        this.model = m;
    }

    public void run() {
       
        try {
            String nomeHost = InetAddress.getLocalHost().getHostName();
            Socket s = new Socket(nomeHost, 8189);
            OutputStream outStream = s.getOutputStream();
            PrintWriter out = new PrintWriter(outStream, true);
            out.println(mail.toString()); //potrebbe anche inviare oggetto mail
            System.out.println("mail inviata dal client");
        } catch (Exception ex) {
            System.out.println(ex);
        }  
        
    }

}
