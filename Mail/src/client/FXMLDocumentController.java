package client;

import common.Email;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField receiver;

    @FXML
    private TextField subject;

    @FXML
    private TextArea body;
    
    @FXML
    private ListView mailList;
    
    @FXML
    private Label userName;
    
    @FXML
    private TextArea selectedMail;
    
    private Model model;
    
    private int selectedIndex;

    int id = 0; //id verrà gestito diversamente

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Email e = new Email(id++, model.getUserName(), receiver.getText(), subject.getText(), body.getText());
        receiver.setText("");
        subject.setText("");
        body.setText("");
        clientThreadOut to = new clientThreadOut (e, model);
        to.start();
    }
    
    @FXML
    private void handleDelete(ActionEvent event) {
        Email e = model.getEmailByIndex(selectedIndex);
        System.out.println("email selezionata da eliminare" + selectedIndex);
        clientThreadOut to = new clientThreadOut (e, model);
        to.start();
        selectedMail.setText("");
    }
    
    @FXML
    private void handleReply(ActionEvent event) {
        Email e = model.getEmailByIndex(selectedIndex);
        receiver.setText(e.getSender());
        subject.setText("RE: " + e.getSubject());
        body.setText("");
    }
    
    @FXML
    private void handleReplyAll(ActionEvent event) {
        //da fare
    }
    
    @FXML
    private void handleForward(ActionEvent event) {
        Email e = model.getEmailByIndex(selectedIndex);
        receiver.setText("");
        subject.setText("FW: " + e.getSubject());
        body.setText(e.getBody());
    }
    
    @FXML
    private void handleSelect (ActionEvent event) {
        selectedIndex = mailList.getSelectionModel().getSelectedIndex();
        Email e = model.getEmailByIndex(selectedIndex);
        selectedMail.setText(e.getSender() + "\n" + e.getSubject() + "\n" + e.getBody());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setText("pluto@mail.com"); //cambiare a seconda dell'utente
        model = new Model();
        model.setUserName(userName.getText());
        modelObserver observer = new modelObserver(mailList);
        model.addObserver(observer);       
        clientThread t = new clientThread(model);
        t.start();
    }

}
