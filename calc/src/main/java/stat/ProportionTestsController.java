package stat;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ProportionTestsController
{
    @FXML private ChoiceBox dropdown;

    ObservableList list = FXCollections.observableArrayList();
    
    private void populate() throws IOException
    {
        String a = "prop > P0";
        String b = "prop != P0";
        String c = "prop < P0";
        list.add(a);
        list.add(b);
        list.add(c);
        dropdown.getItems().addAll(list);
    }
    
    @FXML public void back() throws IOException
    {
        App.setRoot("proportion_select");
    }
}
