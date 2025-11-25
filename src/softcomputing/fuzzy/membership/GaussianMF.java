package softcomputing.fuzzy.membership;

public class GaussianMF implements MembershipFunction {
    private double mean;
    private double sigma;

    public GaussianMF(double mean, double sigma) {
        this.mean = mean;
        this.sigma = sigma;
    }

    @Override
    public double compute(double x) {
        return 0;
    }

    @Override
    public double getStart() {
        return mean - 3 * sigma;
    }

    @Override
    public double getEnd() {
        return mean + 3 * sigma;
    }
}
