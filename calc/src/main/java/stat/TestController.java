package stat;

import java.io.IOException;
import javafx.fxml.FXML;

public class TestController
{
    @FXML public void switchToChiSquared() throws IOException
    {
        App.setRoot("chi_squared");
    }
    @FXML public void switchToGOF() throws IOException
    {
        App.setRoot("chi_squared_gof");
    }
    @FXML public void switchToHomogeneity() throws IOException
    {
        App.setRoot("chi_squared_homogeneity");
    }
    @FXML public void switchToIndependence() throws IOException
    {
        App.setRoot("chi_squared_association");
    }


    @FXML
    public void switchToMeans() throws IOException
    {
        App.setRoot("means_select");
    }

    @FXML
    public void switchToProportions() throws IOException
    {
        App.setRoot("proportions_select");
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