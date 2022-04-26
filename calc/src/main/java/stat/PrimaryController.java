package stat;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML private Label label;

    @FXML
    private void switchToTests() throws IOException {
        App.setRoot("tests");
    }

    @FXML
    private void switchToNormCdf() throws IOException
    {
        App.setRoot("normCdf_select");
    }
    
    @FXML public void switchToDemo() throws IOException
    {
        App.setRoot("denmo");
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("primary");
    }
}
