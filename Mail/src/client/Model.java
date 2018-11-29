package client;

import common.Email;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    
    private String userName;
    private ArrayList<Email> listMail;
    private ArrayList<String> preview;
    
    public void setNewMail(Email e){
        listMail.add(e);
        setPreview();
        setChanged();
        notifyObservers(preview);
    }
    
    public Email getEmailByIndex(int i){
        return listMail.get(i);
    }
    
    public void setPreview(){
        this.preview = new ArrayList<>();
        for(Email e : this.listMail)
            preview.add(e.getSender()+"\n"+e.getSubject());
    }
    
    public void setMailList(ArrayList<Email> list){
        this.listMail = list;
        setPreview();
        setChanged();
        notifyObservers(preview);
    }
    
    public void setUserName(String u){
        userName = u;
    }
    
    public String getUserName(){
        return userName;
    }
    
}
