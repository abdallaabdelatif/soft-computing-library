package softcomputing.fuzzy.variable;

import softcomputing.fuzzy.membership.MembershipFunction;

public class FuzzySet {
    private String name;
    private MembershipFunction mf;

    public FuzzySet(String name, MembershipFunction mf) {
        this.name = name;
        this.mf = mf;
    }

    public String getName() {
        return name;
    }

    public MembershipFunction getMembershipFunction() {
        return mf;
    }

    public double computeMembership(double x) {
        return mf.compute(x);
    }
}
