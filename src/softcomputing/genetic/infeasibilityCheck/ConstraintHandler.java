package softcomputing.genetic.infeasibilityCheck;

import softcomputing.genetic.chromosome.Chromosome;

public interface ConstraintHandler<T> {
    boolean isFeasible(Chromosome<T> chromosome);
}

