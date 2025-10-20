package softcomputing.genetic.operators;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;

import java.util.List;

public interface SelectionStrategy <T>{
    List<Chromosome<T>> select(Population<T> population, int numberOfParents);
}
