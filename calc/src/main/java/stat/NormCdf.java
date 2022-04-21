package stat;

import java.io.IOException;
import javafx.fxml.FXML;
import java.util.Arrays;
import static java.lang.System.out;
import javafx.scene.control.TextField;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormCdf
{
    @FXML private TextField mu_input;
    @FXML private TextField sd_input;
    @FXML private TextField lb_input;
    @FXML private TextField ub_input;
    
    /* this is from data
    @FXML
    public void normCdf() throws IOException
    {
        out.println("--Performing Norm Cdf--");
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
    }*/
    
    @FXML
    public void normCdf() throws IOException
    {
        double mean = Double.parseDouble(mu_input.getText());
        double sd = Double.parseDouble(sd_input.getText());
        NormalDistribution N = new NormalDistribution(mean, sd);
        
        double ub = Double.parseDouble(ub_input.getText());
        double lb = Double.parseDouble(lb_input.getText());
        return N.cumulativeProbability(lb, ub);
    }

    @FXML
    public void back() throws IOException
    {
        App.setRoot("primary");
    }
}
