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
    public void switchToNumerical() throws IOException
    {
        App.setRoot("numerical_tests");
    }

    @FXML
    public void switchToProportional() throws IOException
    {
        App.setRoot("proportional_tests");
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