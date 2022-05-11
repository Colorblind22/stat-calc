package stat;

import java.io.IOException;

import org.apache.commons.math3.distribution.NormalDistribution;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import static java.lang.System.out;

public class ProportionTestsController
{
    @FXML private ChoiceBox<String> dropdown;

    @FXML private TextField x_input;
    @FXML private TextField n_input;
    @FXML private TextField P0_input;

    @FXML private Label label;
    
    private double process()
    {
        String compare = dropdown.getValue();
        switch(compare)
        {
            case "prop > P0":
                return -1d;
            case "prop < P0":
                return 0d;
            default:
                return 2d;
        }
    }

    @FXML public void one_prop_z() throws IOException
    {
        double x = Double.parseDouble(x_input.getText());
        double n = Double.parseDouble(n_input.getText());
        double pi = Double.parseDouble(P0_input.getText());
        out.printf("inputs:\n\tx: %f\n\tn: %f\n\tP0: %f\n",x,n,pi);

        double prop = x/n;
        double error = Math.sqrt((prop*(1-prop))/n);
        out.printf("calculated values:\n\tp-hat: %f\n\tSE: %f\n\t", prop, error);

        NormalDistribution norm = new NormalDistribution();
        double z = (prop-pi)/Math.sqrt(pi*(1-pi)/n);
        double eval = norm.cumulativeProbability(z);
        out.printf("z: %f\n\tp-value: %f\n", z, eval);

        eval = process() == 2d ? eval*2 : Math.abs(process() +eval);
        label.setText(String.format("%.6f",eval));
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("proportion_select");
    }
}
