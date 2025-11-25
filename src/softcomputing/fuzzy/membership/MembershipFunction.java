package softcomputing.fuzzy.membership;

public interface MembershipFunction {
    double compute(double x);
    double getStart();
    double getEnd();
}
