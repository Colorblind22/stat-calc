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
    @FXML public void switchToTwoWay() throws IOException
    {
        App.setRoot("chi_squared_two_way");
    }

    @FXML public void switchToMeans() throws IOException
    {
        App.setRoot("means_select");
    }
    @FXML public void switchToOneSampleT() throws IOException
    {
        App.setRoot("one_mean_t_test");
    }
    @FXML public void switchToTwoSampleT() throws IOException
    {
        App.setRoot("two_mean_t_test");
    }

    @FXML public void switchToProportions() throws IOException
    {
        App.setRoot("proportion_select");
    }
    @FXML public void switchToOnePropZ() throws IOException
    {
        App.setRoot("one_prop_z_test");
    }
    @FXML public void switchToTwoPropZ() throws IOException
    {
        App.setRoot("two_prop_z_test");
    }


    @FXML public void back() throws IOException
    {
        App.setRoot("tests");
    }
    @FXML public void mainMenu() throws IOException
    {
        App.setRoot("primary");
    }
}