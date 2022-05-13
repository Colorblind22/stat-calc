package stat;

import java.io.IOException;

import org.apache.commons.math3.distribution.TDistribution;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class MeanTestsController 
{
    @FXML private TextField mu_input;
    @FXML private TextField xbar_input;
    @FXML private TextField sx_input;
    @FXML private TextField n_input;

    @FXML private TextField sd1_input;
    @FXML private TextField sd2_input;
    @FXML private TextField n1_input;
    @FXML private TextField n2_input; 
    
    @FXML private ChoiceBox<String> dropdown;

    @FXML private Label label;
    
    private double process()
    {
        String compare = dropdown.getValue();
        switch(compare)
        {
            case "μ > μ0":
                return -1d;
            case "μ < μ0":
                return 0d;
            default:
                return 2d;
        }
    }

    @FXML public void one_sample_t() throws IOException
    {
        //label.setText(String.format("%.6f", process() == 2d ? new TDistribution(Double.parseDouble(n_input.getText())-1).cumulativeProbability((Double.parseDouble(xbar_input.getText())-Double.parseDouble(mu_input.getText()))/Math.sqrt(Double.parseDouble(sx_input.getText())/Double.parseDouble(n_input.getText())))*2 : Math.abs(process() + new TDistribution(Double.parseDouble(n_input.getText())-1).cumulativeProbability((Double.parseDouble(xbar_input.getText())-Double.parseDouble(mu_input.getText()))/Math.sqrt(Double.parseDouble(sx_input.getText())/Double.parseDouble(n_input.getText()))))));
        double mu = Double.parseDouble(mu_input.getText());
        double xbar = Double.parseDouble(xbar_input.getText());
        double sx = Double.parseDouble(sx_input.getText());
        double n = Double.parseDouble(n_input.getText());

        TDistribution dist = new TDistribution(n-1);
        double t = (xbar-mu)/(sx/Math.sqrt(n));
        double eval = dist.probability(t);
        eval = process() == 2d ? eval*2 : Math.abs(process() +eval); // i forgor 💀
        label.setText(String.format("%.6f", eval));
    }
    @FXML public void two_sample_t() throws IOException
    {
        label.setText(String.format("%.6f", process() == 2d ? new TDistribution(Double.parseDouble(n_input.getText())-1).cumulativeProbability((Double.parseDouble(xbar_input.getText())-Double.parseDouble(mu_input.getText()))/Math.sqrt((Math.pow(Double.parseDouble(sd1_input.getText()),2)/Double.parseDouble(n1_input.getText()))+((Math.pow(Double.parseDouble(sd2_input.getText()),2)/Double.parseDouble(n2_input.getText()))))*2) : Math.abs(process() + 0)));
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("means_select");
    }
}
