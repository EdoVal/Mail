package server;

import common.Email;
import java.util.*;
import javafx.scene.control.TextArea;

public class modelObserver implements Observer {

    private TextArea log;

    public modelObserver(TextArea log) {
        this.log = log;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            log.setText(log.getText() + (String) arg + "\n");
        }
        if (arg instanceof ArrayList){
            
        }

    }

}
