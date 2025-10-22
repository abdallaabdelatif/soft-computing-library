package softcomputing.genetic.fitness;

import softcomputing.genetic.chromosome.Chromosome;

public interface FitnessFunction <T>{
    double evaluate(Chromosome<T> chromosome);
}
