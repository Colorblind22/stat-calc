package stat;

public class BasicStats 
{
    private double avg(double a, double b)
    {
        return (a+b)/2;
    }
    
    public double mean(double[] nums)
    {
        double sum = 0;
        for(double num:nums)
            sum += num;
        return sum/nums.length;
    }

    public double variance(double[] nums)
    {
        double mu = mean(nums);
        int n = nums.length;

        double sum = 0;
        for(double num : nums)
            sum += Math.pow((num-mu),2.0);
            
        return sum/n;
    }

    public double sd(double[] nums)
    {
        return Math.sqrt(variance(nums));
    }

    public double median(double[] nums)
    {
        int n = nums.length;
        if(n%2!=0)
            return nums[(n/2)];
        else
            return avg(nums[n/2], nums[(n/2)-1]);
    }

    // i dont think i need these for anything but i'll keep the methods defined jic i do
    /*
    public double q1(double[] nums)
    {
        int n = nums.length;
    }

    public double q2(double[] nums)
    {
        int n = nums.length;
    }
    */
}
