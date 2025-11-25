package softcomputing.fuzzy.operators;

public class ProbabilisticSumOr implements OrOperator {
    @Override
    public double apply(double a, double b) {
        return (a+b-(a*b));
    }
}
