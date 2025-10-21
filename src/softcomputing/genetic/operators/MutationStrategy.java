package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;

public interface MutationStrategy<T> {
    void mutate(Chromosome<T> chromosome, double mutationRate);
}