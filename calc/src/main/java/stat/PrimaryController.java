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
        App.setRoot("normCdf");
    }

    
}
