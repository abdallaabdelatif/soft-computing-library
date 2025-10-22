package softcomputing.genetic.operators;
import java.util.List;

import softcomputing.genetic.chromosome.Chromosome;
import softcomputing.genetic.population.Population;


public class SteadyStateReplacement<T> implements ReplacementStrategy<T>{
    @Override
    public Population<T> replace(Population<T> currentPopluation, Population<T> offsprings, int eliteCount){
        Population<T> newPopulation = new Population<>();
        int k = offsprings.size();
        int popSize = currentPopluation.size();

        for (int i = 0; i < k; i++) {
            Chromosome<T> child = offsprings.getIndividuals().get(i);
            newPopulation.addIndividual(child);
        }

        int i = 0;
        while (newPopulation.size() < popSize && i < currentPopluation.size()) {
            newPopulation.addIndividual(currentPopluation.getIndividuals().get(i));
            i++;
        }
        return newPopulation;
    }
}