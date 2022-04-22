package stat;

import java.io.IOException;
import javafx.fxml.FXML;

public class TestController
{
    @FXML
    public void switchToChiSquared() throws IOException
    {
        App.setRoot("chi_squared");
    }

    @FXML
    public void switchToZ() throws IOException
    {
        App.setRoot("z_tests");
    }

    @FXML
    public void switchToT() throws IOException
    {
        App.setRoot("t_tests");
    }

    @FXML
    public void back() throws IOException
    {
        App.setRoot("tests");
    }

    @FXML
    public void mainMenu() throws IOException
    {
        App.setRoot("primary");
    }
}