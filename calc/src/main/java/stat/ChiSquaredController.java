package stat;

/* TODO
    implement matrix entry and processing
    do the funny testing
*/

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import static java.lang.System.out;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class ChiSquaredController 
{
    @FXML private TextField observed_input;
    @FXML private TextField expected_input;
    @FXML private TextField df_input;

    @FXML private Label chi_squared_label;
    @FXML private Label p_value_label;

    private double calc_chi_squared(double[] obs, double[] exp)
    {
        double sum = 0;
        for(int i = 0; i < obs.length; i++)
        {
            sum += Math.pow((obs[i]-exp[i]),2)/exp[i];
        }
        return sum;
    }

    @FXML public void GOF_test()
    {
        out.println("--Performing Chi Squared GOF--");
        ChiSquaredDistribution cs = new ChiSquaredDistribution(
            Double.parseDouble(df_input.getText())
        );

        String[] obsStrings = observed_input.getText().split(" ");
        String[] expStrings = expected_input.getText().split(" ");
        out.printf("split strings :\n\t%s\n\t%s\n",Arrays.toString(obsStrings),Arrays.toString(expStrings));
        double[] observed = new double[obsStrings.length];
        double[] expected = new double[expStrings.length];
        for(int i = 0; i < obsStrings.length; i++)
        {
            observed[i] = Double.parseDouble(obsStrings[i]);
            expected[i] = Double.parseDouble(expStrings[i]);
        }
        out.printf("double lists :\n\t%s\n\t%s\n",Arrays.toString(observed),Arrays.toString(expected));
        double value = calc_chi_squared(observed, expected);
        out.printf("chi squared :\n\t%f",value);
        double eval = cs.probability(value);
        out.printf("p-value :\n\t%f", eval);

        chi_squared_label.setText(""+value);
        p_value_label.setText(""+eval);
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("chi_squared");
    }
}
