package stat;

import java.io.IOException;
import javafx.fxml.FXML;
import java.util.Arrays;
import static java.lang.System.out;
import javafx.scene.control.TextField;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormCdf
{
    @FXML private TextField input;
    
    @FXML
    public void normCdf() throws IOException
    {
        out.println("--Performing Norm Cdf--");
        //BasicStats bs = new BasicStats();

        String datum = input.getText();
        String[] data = datum.split(" ");
        out.printf("Split string : %s\n", Arrays.toString(data));

        double[] nums = new double[data.length];
        for(int i = 0; i < nums.length; i++)
            nums[i] = Double.parseDouble(data[i]);
        out.printf("Number list : %s\n", Arrays.toString(nums));

        // double mu = bs.mean(nums);
        // double sigma = bs.sd(nums);

        /* 
        TODO
        -learn what integrals are
        -learn how to somehow implement that into java
        -get it to work :)

        -maybe do norm pdf instead for the time being

        https://en.wikipedia.org/wiki/Integral
        https://wikimedia.org/api/rest_v1/media/math/render/svg/ddb34c627654227d595a7b0cecc9a631da7a8db7
        */
    }

    @FXML
    public void back() throws IOException
    {
        App.setRoot("primary");
    }
}