package stat;

import static java.lang.System.out;
import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormCdf
{
    @FXML private TextField mu_input;
    @FXML private TextField sd_input;
    @FXML private TextField lb_input;
    @FXML private TextField ub_input;
    
    @FXML private TextField input;

    @FXML private Label label;

    @FXML
    public void normCdf_data() throws IOException
    {
        out.println("--Performing Norm Cdf from Data--");
        BasicStats bs = new BasicStats();

        String datum = input.getText();
        String[] data = datum.split(" ");
        out.printf("Split string : %s\n", Arrays.toString(data));

        double[] nums = new double[data.length];
        for(int i = 0; i < nums.length; i++)
            nums[i] = Double.parseDouble(data[i]);
        out.printf("Number list : %s\n", Arrays.toString(nums));

        double mu = bs.mean(nums);
        double sigma = bs.sd(nums);

        NormalDistribution N = new NormalDistribution(mu, sigma);

        double ub = Double.parseDouble(ub_input.getText());
        double lb = Double.parseDouble(lb_input.getText());
        double eval = N.probability(lb, ub);
        out.printf("normCdf(%f, %f, %f, %f) = %.6f\n", mu, sigma, lb, ub, eval);
        label.setText(String.format("%.6f",eval));
    }

    @FXML
    public void normCdf_stats() throws IOException
    {
        out.println("--Performing NormCdf from Stats--");
        double mean = Double.parseDouble(mu_input.getText());
        double sd = Double.parseDouble(sd_input.getText());
        NormalDistribution N = new NormalDistribution(mean, sd);
        
        double ub = Double.parseDouble(ub_input.getText());
        double lb = Double.parseDouble(lb_input.getText());
        double eval = N.probability(lb, ub);
        out.printf("normCdf(%f, %f, %f, %f) = %.6f\n", mean, sd, lb, ub, eval);
        label.setText(String.format("%.6f",eval));
    }

    @FXML
    public void switchToNormCdfData() throws IOException
    {
        App.setRoot("normCdf_data");
    }

    @FXML
    public void switchToNormCdfStats() throws IOException
    {
        App.setRoot("normCdf_stats");
    }

    @FXML
    public void backToNormCdfSelect() throws IOException
    {
        App.setRoot("normCdf_select");
    }

    @FXML
    public void back() throws IOException
    {
        App.setRoot("primary");
    }
}
