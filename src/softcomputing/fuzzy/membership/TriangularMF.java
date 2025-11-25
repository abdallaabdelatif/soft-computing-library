package softcomputing.fuzzy.membership;

public class TriangularMF implements MembershipFunction {
    private double a; // left point
    private double b; // peak
    private double c; // right point

    public TriangularMF(double a, double b, double c) {
        if (a > b || b > c) {
            throw new IllegalArgumentException("Invalid triangular MF points must have: a <= b <= c");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double compute(double x) {
        if (x <= a || x >= c) {
            return 0.0;
        }
        if (x > a && x < b) {
            return (x - a) / (b - a);
        }
        if (x == b) {
            return 1.0;
        }
        if (x > b && x < c) {
            return (c - x) / (c - b);
        }
        return 0.0;
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
