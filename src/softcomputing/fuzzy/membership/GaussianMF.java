package softcomputing.fuzzy.membership;

public class GaussianMF implements MembershipFunction {
    private double mean;
    private double sigma;

    public GaussianMF(double center, double sigma) {
        if (sigma <= 0) {
            throw new IllegalArgumentException("Sigma must be > 0");
        }
        this.mean = center;
        this.sigma = sigma;
    }

    @Override
    public double compute(double x) {
        double numerator = (x - mean) * (x - mean);
        double denominator = 2 * sigma * sigma;
        return Math.exp(- numerator / denominator);
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
