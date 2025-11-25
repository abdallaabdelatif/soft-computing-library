package softcomputing.fuzzy.membership;

public class TrapezoidalMF implements MembershipFunction {
    private double a, b, c, d;

    public TrapezoidalMF(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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
        return d;
    }
}
