package stat;

import java.io.IOException;

import org.apache.commons.math3.distribution.TDistribution;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import static java.lang.System.out;

public class MeanTestsController 
{
    @FXML private TextField mu_input;
    @FXML private TextField xbar_input;
    @FXML private TextField sx_input;
    @FXML private TextField n_input;


    @FXML private TextField xbar1_input;
    @FXML private TextField xbar2_input;
    @FXML private TextField sd1_input;
    @FXML private TextField sd2_input;
    @FXML private TextField n1_input;
    @FXML private TextField n2_input; 
    
    @FXML private ChoiceBox<String> dropdown;

    @FXML private Label label;
    
    private double process_one_sample()
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
        out.printf("inputs:\n\tmu: %.6f\n\txbar: %.6f\n\tsx: %.6f\n\tn: %.6f\n", mu,xbar,sx,n);

        TDistribution dist = new TDistribution(n-1);
        double t = (xbar-mu)/(sx/Math.sqrt(n));
        double eval = dist.cumulativeProbability(t);
        out.printf("results :\n\tt: %.6f\n", t);
        if(process_one_sample() == 2d)
            eval = eval >= .5d ? (1-eval)*2 : eval*2;
        else
            eval = process_one_sample() == 2d ? eval*2 : Math.abs(process_one_sample() +eval); // i forgor 💀
        out.printf("\tp-value: %.6f\n", eval);
        label.setText(String.format("%.6f", eval));
    }
    
    private double process_two_sample()
    {
        String compare = dropdown.getValue();
        switch(compare)
        {
            case "μ1 > μ2":
                return -1d;
            case "μ1 < μ2":
                return 0d;
            default:
                return 2d;
        }
    }
    
    private double two_sample_df(double s1, double n1, double s2, double n2)
    {
        double v1 = Math.pow(s1, 2);
        double v2 = Math.pow(s2, 2);
        return (Math.pow((v1/n1 + v2/n2),2))/(Math.pow(v1/n1,2)/(n1-1)+Math.pow(v2/n2,2)/n2-1);
    }
    
    @FXML public void two_sample_t() throws IOException
    {
        //label.setText(String.format("%.6f", process() == 2d ? new TDistribution(Double.parseDouble(n_input.getText())-1).cumulativeProbability((Double.parseDouble(xbar_input.getText())-Double.parseDouble(mu_input.getText()))/Math.sqrt((Math.pow(Double.parseDouble(sd1_input.getText()),2)/Double.parseDouble(n1_input.getText()))+((Math.pow(Double.parseDouble(sd2_input.getText()),2)/Double.parseDouble(n2_input.getText()))))*2) : Math.abs(process() + 0)));
        double xbar1=Double.parseDouble(xbar1_input.getText());
        double sd1=Double.parseDouble(sd1_input.getText());
        double n1=Double.parseDouble(n1_input.getText());
        double xbar2=Double.parseDouble(xbar2_input.getText());
        double sd2=Double.parseDouble(sd2_input.getText());
        double n2=Double.parseDouble(n2_input.getText());
        out.printf("inputs:\n\t1\n\txbar %.6f\n\tsd %.6f\n\tn %.6f\n\t2\n\txbar %.6f\n\tsd %.6f\n\tn %.6f\n", xbar1, sd1, n1, xbar2, sd2, n2);
        double df = two_sample_df(sd1, n1, sd2, n2);
        
        TDistribution dist = new TDistribution(df);


        double v1 = sd1*sd1;
        double v2 = sd2*sd2;
        double t = (xbar1-xbar2)/Math.sqrt((v1/n1)+(v2/n2));
        double eval = dist.cumulativeProbability(t);
        if(process_two_sample() == 2d)
            eval = eval < .5d ? eval*2 : (1-eval)*2;
        else
            eval = Math.abs(process_two_sample() +eval); // i forgor 💀
        out.printf("results:\n\tt %.6f\n\tp-value %.6f", t, eval);
        label.setText(String.format("%.6f",eval));
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("means_select");
    }
}
