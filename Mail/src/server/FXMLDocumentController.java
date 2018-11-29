package server;

import common.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea log;

    private Model model;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        model.setNewLine("Asked new server");
        ServerSocket s;
        try {
            s = new ServerSocket(8189);
            serverThread t = new serverThread(s, model);
            t.start();
            serverThreadIn si = new serverThreadIn(s, model);
            si.start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = new Model();
        modelObserver observer = new modelObserver(log);
        model.addObserver(observer);
        model.setNewLine("Welcome!");
        BufferedReader reader = null;
        try {
            int c = 0;
            reader = new BufferedReader(new FileReader("mail.txt"));
            String line = reader.readLine();
            while (line != null) {
                model.setNewMail(new Email(line));
                line = reader.readLine();
                c++;
            }
            model.setNewLine(c + " Emails loaded");
        } catch (IOException ex) {
            System.out.println(ex);
            model.setNewLine("File not found");
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
