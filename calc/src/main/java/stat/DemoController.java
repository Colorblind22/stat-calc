package stat;

import javafx.fxml.FXML;
import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class DemoController 
{
    @FXML TextField size;
    @FXML Button button;

    @FXML public void generate() throws IOException
    {
        App.setRoot("");
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("primary");
    }
}
