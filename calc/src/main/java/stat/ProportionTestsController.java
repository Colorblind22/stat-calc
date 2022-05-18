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

    @FXML private TextField x1_input;
    @FXML private TextField n1_input;
    @FXML private TextField x2_input;
    @FXML private TextField n2_input;

    @FXML private Label label;
    
    private double process_one_sample()
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

        if(process_one_sample() == 2d)
            eval = eval < .5d ? eval*2 : (1-eval)*2;
        else
            eval = Math.abs(process_one_sample() +eval); // i forgor 💀
        label.setText(String.format("%.6f",eval));
    }

    private double process_two_sample()
    {
        String compare = dropdown.getValue();
        switch(compare)
        {
            case "P1 > P2":
                return -1d;
            case "P1 < P2":
                return 0d;
            default:
                return 2d;
        }
    }

    @FXML public void two_prop_z() throws IOException
    {
        double x1 = Double.parseDouble(x1_input.getText());
        double x2 = Double.parseDouble(x2_input.getText());
        double n1 = Double.parseDouble(n1_input.getText());
        double n2 = Double.parseDouble(n2_input.getText());

        double p1 = x1/n1;
        double p2 = x2/n2;
        out.printf("inputs:\n\tx1: %.6f\n\tn1: %.6f\n\tx2: %.6f\n\tn2: %.6f\n",x1,n1,x2,n2);
        double c = (x1+x2)/(n1+n2);
        double error = Math.sqrt(c * (1-c)*((1/n1)+(1/n2)));
        
        NormalDistribution dist = new NormalDistribution();

        double z = (p1-p2)/error;
        out.printf("calculated values:\n\tp1: %.6f\n\tp2: %.6f\n\tpc: %.6f\n\terror: %.6f\n\tz: %.6f\n",p1,p2,c,error,z);
        double eval = dist.cumulativeProbability(z);
        if(process_two_sample() == 2d)
            eval = eval < .5d ? eval*2 : (1-eval)*2;
        else
            eval = Math.abs(process_two_sample() +eval); // i forgor 💀
        out.printf("\tp-value: %.6f\n",eval);
        label.setText(String.format("%.6f",eval));
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("proportion_select");
    }
}
