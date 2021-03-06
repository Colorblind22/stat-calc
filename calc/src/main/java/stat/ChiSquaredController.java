package stat;

import java.io.IOException;
import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import static java.lang.System.out;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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
        button.setVisible(false);
    }

    @FXML public void two_way_test() throws IOException
    {
        out.println("--Performing chi_squared two-way test--");
        double[][] mat = new double[rows][cols];
        int n = 0;
        ObservableList<Node> children = matrix.getChildren();
        out.printf("rows : %d\ncols : %d\n",rows,cols);

        int df = (rows-1)*(cols-1);
        out.printf("df : %d\n", df);
        ChiSquaredDistribution cs = new ChiSquaredDistribution(df);

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                mat[i][j] = Integer.parseInt(((TextField)children.get(n++)).getText());
            }
        } // all i can say is; i am so cool this worked first try
        out.println("matrix :");
        for(double[] arr : mat)
            out.printf("\t%s\n", Arrays.toString(arr));

        double[][] expMat = new double[rows][cols]; // expected
        double[] rowTotals = new double[rows];
        double[] colTotals = new double[cols];
        for(int x = 0; x < rows; x++)
            rowTotals[x] = sum(mat[x]);
        for(int y = 0; y < expMat[0].length; y++)
            for(int z = 0; z < expMat.length; z++)
                colTotals[y] += mat[z][y];
        out.printf("totals :\n\trow totals : %s\n\tcol totals :%s\n", Arrays.toString(rowTotals), Arrays.toString(colTotals));
        
        double total = 0;
        for(int p = 0; p < rows; p++)
            total += sum(mat[p]);
        out.printf("table total : %.6f\n", total);

        for(int k = 0; k < rows; k++)
            for(int l = 0; l < cols; l++)
                expMat[k][l] = (rowTotals[k]*colTotals[l])/total;
        out.println("expected matrix :");
        for(double[] expArr : expMat)
            out.printf("\t%s\n", Arrays.toString(expArr));

        double chi_squared = 0;
        for(int q = 0; q < rows; q++)
            for(int w = 0; w < cols; w++)
                chi_squared += Math.pow(mat[q][w]-expMat[q][w],2)/expMat[q][w];
        
        double eval = 1-cs.cumulativeProbability(chi_squared);
        out.printf("results :\n\tchi_squared : %.6f\n\tp_value : %.6f\n",chi_squared,eval);
        chi_squared_label.setText(String.format("Chi-squared : %.6f",chi_squared));
        p_value_label.setText(String.format("df : %d\tP-value : %.6f",df, eval));
        out.println("--Chi_squared two-way test complete--");
    }

    private double sum(double[] a)
    {
        double sum = 0;
        for(double d : a)
            sum += d;
        return sum;
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
        out.println("--Performing chi2 GOF--");
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
        double eval = 1-cs.cumulativeProbability(value);
        out.printf("p-value :\n\t%f\n", eval);

        chi_squared_label.setText(String.format("%.6f", value));
        p_value_label.setText(String.format("%.6f", eval));

        out.printf("--chi2 GOF Test over--\n");
    }

    @FXML public void back() throws IOException
    {
        App.setRoot("chi_squared");
    }
}
