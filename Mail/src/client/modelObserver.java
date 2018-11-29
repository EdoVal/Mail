package client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class modelObserver implements Observer{
    
    ListView mailList;
    
    public modelObserver (ListView mailList){
        this.mailList = mailList;
    }
    
    @Override
    public void update(Observable o, Object arg){
        Model model = (Model) o;
        ArrayList<String> tmp = (ArrayList<String>)arg;
        ObservableList<String> data = observableArrayList(tmp);
        mailList.setItems(data);
    }
    
}
