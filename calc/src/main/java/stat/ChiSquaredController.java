package stat;

/* TODO
    implement matrix entry and processing
    do the funny testing
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import static java.lang.System.out;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Pos;
import javafx.scene.Node;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

public class ChiSquaredController 
{
    @FXML private TextField observed_input;
    @FXML private TextField expected_input;
    @FXML private TextField df_input;

    @FXML private Label chi_squared_label;
    @FXML private Label p_value_label;

    @FXML private TextField size;
    @FXML private GridPane matrix;
    @FXML private Button button;

    private int rows;
    private int cols;

    @FXML public void generate() throws IOException
    {   
        String[] nums = size.getText().split(" ");
        int i = Integer.parseInt(nums[0]);
        int j = Integer.parseInt(nums[1]);
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < j; x++) {
                // Create a new TextField in each Iteration
                TextField tf = new TextField();
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(true);
                matrix.setRowIndex(tf, y);
                matrix.setColumnIndex(tf, x);
                matrix.getChildren().add(tf);
            }
        }
        rows=i;
        cols=j;
    }

    @FXML public void two_way_test() throws IOException
    {
        int[][] mat = new int[rows][cols];
        int n = 0;
        ObservableList<Node> children = matrix.getChildren();

        //this loop for demonstrating matrix input works
        for(Node d : children)
            out.println(((TextField) d).getText());

        // this loop is a work in progress for two-way test
        for(int i = 0; i < cols; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                mat[i][j] = Integer.parseInt(((TextField)children.get(n++)).getText());
            }
        }
    }

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
        out.println("--Performing χ2 GOF--");
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
        out.printf("chi squared :\n\t%f\n",value);
        double eval = cs.probability(value);
        out.printf("p-value :\n\t%f\n", eval);

        chi_squared_label.setText(String.format("%.6f", value));
        p_value_label.setText(String.format("%.6f", eval));

        out.printf("--χ2 GOF Test over--\n");
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("chi_squared");
    }
}
