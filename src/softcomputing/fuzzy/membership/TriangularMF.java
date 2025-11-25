package softcomputing.fuzzy.membership;

public class TriangularMF implements MembershipFunction {
    private double a; // left point
    private double b; // peak
    private double c; // right point

    public TriangularMF(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double compute(double x) {
        return 0;
    }

    @Override
    public double getStart() {
        return a;
    }

    @Override
    public double getEnd() {
        return c;
    }
}
