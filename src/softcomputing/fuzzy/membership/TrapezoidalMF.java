package softcomputing.fuzzy.membership;

public class TrapezoidalMF implements MembershipFunction {
    private double a, b, c, d;

    public TrapezoidalMF(double a, double b, double c, double d) {
        if (!(a <= b && b <= c && c <= d)) {
            throw new IllegalArgumentException("Invalid trapezoid MF points must have: a <= b <= c <= d");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double compute(double x) {
        if (x <= a || x >= d) {
            return 0.0;
        }
        if (x > a && x < b) {
            return (x - a) / (b - a);
        }
        if (x >= b && x <= c) {
            return 1.0;
        }
        if (x > c && x < d) {
            return (d - x) / (d - c);
        }
        return 0.0;
    }

    @Override
    public double getStart() {
        return a;
    }

    @Override
    public double getEnd() {
        return d;
    }
}