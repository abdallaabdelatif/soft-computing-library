package softcomputing.genetic.operators;

import softcomputing.genetic.population.Population;

public interface ReplacementStrategy <T>{
    public Population<T> replace(Population<T> currentPopluation, Population<T> offsprings, int eliteCount);
}
