package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;

public interface CrossoverStrategy <T>{
    public void operate(Chromosome<T> parent1, Chromosome<T> parent2, Chromosome<T> child1, Chromosome<T> child2);
}
